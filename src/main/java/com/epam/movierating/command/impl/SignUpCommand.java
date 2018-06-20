package com.epam.movierating.command.impl;

import com.epam.movierating.command.Command;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.HashPasswordUtil;

public class SignUpCommand implements Command {

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";
    private static final String NAME_PARAM = "name";


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

        User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setName(name);

        /**
         * Here will be validation  */

        userService.signUp(user);

        return new RequestResult("jsp/login.jsp", NavigationType.REDIRECT);
    }

}
