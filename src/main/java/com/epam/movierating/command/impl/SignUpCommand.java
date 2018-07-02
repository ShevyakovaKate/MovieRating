package com.epam.movierating.command.impl;

import com.epam.movierating.command.Command;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.HashPasswordUtil;
import com.epam.movierating.util.ValidationUtil;

public class SignUpCommand implements Command {

    private static final String ERROR_SIGNUP_MESSAGE = "errorSignUpMessage";
    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";
    private static final String EMAIL_ERROR_MESSAGE = "message.email.error";
    private static final String NAME_ERROR_MESSAGE = "message.name.error";
    private static final String PASSWORD_ERROR_MESSAGE = "message.passport.error";


    private UserService userService;

    public SignUpCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent content) throws ServiceException {
        String email = content.getRequestParameterByName(EMAIL_PARAM);
        String enterPassword = content.getRequestParameterByName(PASSWORD_PARAM);
        String name = content.getRequestParameterByName(NAME_PARAM);

        String password = HashPasswordUtil.sha1(enterPassword);


        if (!ValidationUtil.isEmailValid(email)) {
            content.setSessionAttributes(ERROR_SIGNUP_MESSAGE, EMAIL_ERROR_MESSAGE);
            return new RequestResult("jsp/homepage.jsp", NavigationType.REDIRECT);
        }

        /*if (userService.isEmailExist(email)) {
            content.setSessionAttributes(ERROR_SIGNUP_MESSAGE, EXIST_EMAIL_ERROR_MESSAGE);
            return new RequestResult(REGISTRATION_PATH, NavigationType.REDIRECT);
        }*/

        if (!ValidationUtil.isNameValid(name)) {
            content.setSessionAttributes(ERROR_SIGNUP_MESSAGE, NAME_ERROR_MESSAGE);
            return new RequestResult("jsp/homepage.jsp", NavigationType.REDIRECT);
        }

        if (!ValidationUtil.isPasswordValid(password)) {
            content.setSessionAttributes(ERROR_SIGNUP_MESSAGE, PASSWORD_ERROR_MESSAGE);
            return new RequestResult("jsp/homepage.jsp", NavigationType.REDIRECT);
        }

       /**
        *
        */

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        userService.signUp(user);

        return new RequestResult("jsp/homepage.jsp", NavigationType.REDIRECT);
    }

}
