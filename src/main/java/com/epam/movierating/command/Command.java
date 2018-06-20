package com.epam.movierating.command;

import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.service.ServiceException;

public interface Command {
    RequestResult execute(RequestContent requestContent) throws ServiceException;
}
