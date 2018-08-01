package com.epam.movierating.controller.command.impl;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.HashPasswordUtil;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

public class LoginCommand implements Command {

    private static final String HOMEPAGE = ConfigurationManager.getProperty(Paths.HOMEPAGE);
    private static final String USER_HOMEPAGE = ConfigurationManager.getProperty(Paths.USER_HOME_PAGE);
    private static final String ADMIN_HOMEPAGE = ConfigurationManager.getProperty(Paths.ADMIN_MOVIE_LIST_PAGE);

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String enterEmail = requestContent.getRequestParameterByName(RequestParam.EMAIL_PARAM);
        String enterPassword = requestContent.getRequestParameterByName(RequestParam.PASSWORD_PARAM);
        String hashPassword = HashPasswordUtil.sha1(enterPassword);

        User user = userService.login(enterEmail, hashPassword);

        if(user == null) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.LOGIN_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        /**
         * ЕСЛИ ЗАБАНЕН ОШИБКУ ВЫВОДИТЬ!!!*/

        requestContent.setSessionAttributes(SessionAtr.USER, user);
        requestContent.setSessionAttributes(SessionAtr.USER_TYPE, user.getRole().toString());

        if(user.getRole() == Role.USER) {
            return new RequestResult(USER_HOMEPAGE, NavigationType.FORWARD);
        }

        return new RequestResult(ADMIN_HOMEPAGE, NavigationType.FORWARD);
    }
}
