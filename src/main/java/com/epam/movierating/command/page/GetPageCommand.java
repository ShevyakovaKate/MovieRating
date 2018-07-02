package com.epam.movierating.command.page;

import com.epam.movierating.command.Command;
import com.epam.movierating.constant.Paths;
import com.epam.movierating.constant.RequestParam;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.service.ServiceException;

public class GetPageCommand implements Command {
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String page = "";
        String jspPageParameter = requestContent.getRequestParameterByName(RequestParam.JSP_PAGE);

        PageType pageType = PageType.fromString(jspPageParameter);
        if(pageType == null) {
            pageType = PageType.NOT_FOUND_PAGE_JSP;
        }
        switch (pageType){
            case HOMEPAGE_JSP:
                page = "jsp/homepage.jsp";
                break;
            case USERHOME_JSP:
                page = "jsp/user/user-home.jsp";
                break;
            case ADMINHOME_JSP:
                page = "jsp/admin/admin-home.jsp";
                break;
            case NOT_FOUND_PAGE_JSP:
                page = "jsp/error/error.jsp";
                break;
        }

        String pagePath = page;
        return new RequestResult(pagePath, NavigationType.REDIRECT);
    }

}
