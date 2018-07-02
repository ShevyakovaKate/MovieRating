package com.epam.movierating.command.impl;

import com.epam.movierating.command.Command;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.HashPasswordUtil;

public class LoginCommand implements Command {

    private static final String EMAIL_PARAM = "email";
    private static final String PASSWORD_PARAM = "password";

    private UserService userService;

    public LoginCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String enterEmail = requestContent.getRequestParameterByName(EMAIL_PARAM);
        String enterPassword = requestContent.getRequestParameterByName(PASSWORD_PARAM);
        String hashPassword = HashPasswordUtil.sha1(enterPassword);

        User user = userService.login(enterEmail, hashPassword);

        if(user == null) {
            requestContent.setSessionAttributes("errorMessage", "vse huevo");
            return new RequestResult("jsp/homepage.jsp", NavigationType.REDIRECT);
        }

        requestContent.setSessionAttributes("user", user);
        requestContent.setSessionAttributes("user_type", user.getRole().toString());

        if(user.getRole() == Role.USER) {
            return new RequestResult("jsp/user/user-home.jsp", NavigationType.REDIRECT);
        }

        return new RequestResult("jsp/admin/admin-home.jsp", NavigationType.REDIRECT);
    }
}
