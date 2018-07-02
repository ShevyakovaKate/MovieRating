package com.epam.movierating.filter;

import com.epam.movierating.command.CommandType;
import com.epam.movierating.command.page.PageType;
import com.epam.movierating.constant.LevelAccess;
import com.epam.movierating.constant.RequestParam;
import com.epam.movierating.constant.SessionAtr;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/controller")
public class SecurityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        HttpSession session = request.getSession();

        String requiredCommand = request.getParameter(RequestParam.COMMAND);
        CommandType command = CommandType.fromString(requiredCommand);

        if(command == null) {
            forwardErrorPage(request,response);
            return;
        }

        String userType = (String)session.getAttribute(SessionAtr.USER_TYPE);
        boolean isGuest = userType == null;

        String commandLevelAccess = command.getLevelAccess();
        boolean isGuestCommand = LevelAccess.GUEST.equalsIgnoreCase(commandLevelAccess);
        boolean isAvailableToAnyCommand = LevelAccess.COMMON.equalsIgnoreCase(commandLevelAccess);
        if(isGuest && !isGuestCommand && !isAvailableToAnyCommand) {
            forwardToMainPage(request, response);
            return;
        }

        if(!isAvailableToAnyCommand && !isGuestCommand) {
            boolean hasUserAccess = commandLevelAccess.equalsIgnoreCase(userType);

            if(!hasUserAccess) {
                forwardBlockAccessPage(request, response);
                return;
            }
        }

        if(CommandType.GETPAGE == command) {
            String jspPageParameter = request.getParameter(RequestParam.JSP_PAGE);
            PageType page = PageType.fromString(jspPageParameter);

            if(page == null) {
                forwardErrorPage(request, response);
                return;
            }

            String pageLevelAccess = page.getLevelAccess();
            boolean hasUserAccess = pageLevelAccess.equalsIgnoreCase(userType);
            boolean isCommonPage = LevelAccess.COMMON.equalsIgnoreCase(pageLevelAccess);
            boolean isGuestPage = LevelAccess.GUEST.equalsIgnoreCase(pageLevelAccess);

            if(!hasUserAccess && !isCommonPage && !isGuestPage) {
                forwardBlockAccessPage(request, response);
                return;
            }
        }
        filterChain.doFilter(servletRequest,servletResponse);

    }

    @Override
    public void destroy() {

    }

    private void forwardErrorPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagePath = "jsp/error/error.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(pagePath);
        requestDispatcher.forward(request, response);

    }

    private void forwardToMainPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagePath = "jsp/homepage.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(pagePath);
        requestDispatcher.forward(request, response);
    }

    private void forwardBlockAccessPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pagePath = "jsp/error/accessdeny.jsp";
        RequestDispatcher requestDispatcher = request.getRequestDispatcher(pagePath);
        requestDispatcher.forward(request, response);

    }
}
