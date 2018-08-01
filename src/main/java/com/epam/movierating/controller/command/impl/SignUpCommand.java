package com.epam.movierating.controller.command.impl;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.HashPasswordUtil;
import com.epam.movierating.util.ValidationUtil;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

public class SignUpCommand implements Command {

    private static final String HOMEPAGE =  ConfigurationManager.getProperty(Paths.HOMEPAGE);

    private UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent content) throws ServiceException {
        String email = content.getRequestParameterByName(RequestParam.EMAIL_PARAM);
        String enterPassword = content.getRequestParameterByName(RequestParam.PASSWORD_PARAM);
        String enterPasswordConfirm = content.getRequestParameterByName(RequestParam.PASSWORD_PARAM_REPEAT);
        String name = content.getRequestParameterByName(RequestParam.NAME_PARAM);

        if (!ValidationUtil.isEmailValid(email)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.EMAIL_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        if (userService.isEmailExist(email)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.EXIST_EMAIL_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isNameValid(name)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.NAME_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isPasswordValid(enterPassword)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PASSWORD_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isPasswordRepeatValid(enterPassword, enterPasswordConfirm)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PASSWORD_REPEAT_ERROR);
            return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
        }

        String password = HashPasswordUtil.sha1(enterPassword);

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        userService.signUp(user);

        content.setRequestAttribute(SessionAtr.OPERATION_STATUS_POSITIVE, Messages.SUCCESS);
        return new RequestResult(HOMEPAGE, NavigationType.FORWARD);
    }

}
