package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;

public class LogoutCommand implements Command {
    private static final String START_PAGE = ConfigurationManager.getProperty(Paths.INDEX_PAGE);

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        requestContent.sessionInvalidate();
        requestContent.setSessionAttributes(SessionAtr.USER, null);
        requestContent.setSessionAttributes(SessionAtr.USER_TYPE, null);
        return new RequestResult(START_PAGE, NavigationType.REDIRECT);
    }
}
