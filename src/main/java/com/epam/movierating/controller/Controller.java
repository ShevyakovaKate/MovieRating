package com.epam.movierating.controller;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.command.factory.FactoryCommand;
import com.epam.movierating.controller.content.HttpRequestHelper;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/controller")
public class Controller extends HttpServlet {

    public Controller() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       processRequest(request, response);
    }

    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        RequestContent content = new RequestContent();
        HttpRequestHelper httpRequestHelper = new HttpRequestHelper(content);
        httpRequestHelper.getDataFromHttpRequest(request);

        FactoryCommand factoryCommand = new FactoryCommand();
        String commandName = content.getCommandName();
        Command command = factoryCommand.createCommand(commandName);

        RequestResult requestResult;
        String page;
        NavigationType navigationType;

        try {
            requestResult = command.execute(content);
            page = requestResult.getPage();
            navigationType = requestResult.getNavigationType();
        } catch (ServiceException e) {
            throw new ServletException("Problem when process request:", e);
        }

        httpRequestHelper.addDataToHttpRequest(request);

        forwardOrRedirect(request, response, page, navigationType);
    }

    private void forwardOrRedirect(HttpServletRequest request, HttpServletResponse response,
                                   String page, NavigationType navigationType) throws ServletException, IOException {
        if(page == null) {
            page = request.getContextPath() + ConfigurationManager.getProperty(Paths.ERROR_PAGE);
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        }

        if (navigationType == NavigationType.FORWARD) {
            RequestDispatcher requestDispatcher = request.getRequestDispatcher(page);
            requestDispatcher.forward(request, response);
        } else if (navigationType == NavigationType.REDIRECT) {
            response.sendRedirect(page);
        }
    }
}
