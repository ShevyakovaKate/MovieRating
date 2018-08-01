package com.epam.movierating.controller.command;

import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.service.ServiceException;

public interface Command {
    RequestResult execute(RequestContent requestContent) throws ServiceException;
}
