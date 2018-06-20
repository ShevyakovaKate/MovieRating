package com.epam.movierating.controller;

import com.epam.movierating.command.Command;
import com.epam.movierating.command.factory.FactoryCommand;
import com.epam.movierating.content.HttpRequestHelper;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.service.ServiceException;

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
        //**here will be logic of forward or redirect*//*
        response.sendRedirect(page);

    }
}