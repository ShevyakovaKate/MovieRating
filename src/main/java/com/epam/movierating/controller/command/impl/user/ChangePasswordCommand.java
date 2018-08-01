package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Role;
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

public class ChangePasswordCommand implements Command {
    private static final String HOMEPAGE_PATH = ConfigurationManager.getProperty(Paths.HOMEPAGE);
    private static final String ADMIN_CHANGE_PASS_PATH = "ksj";
    private static final String CLIENT_CHANGE_PASS_PATH = ConfigurationManager.getProperty(Paths.USER_CHANGE_PASS_PAGE);

    private UserService userService;

    public ChangePasswordCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent content) throws ServiceException {
        String newPassword = content.getRequestParameterByName(RequestParam.NEW_PASSWORD_PARAM);
        String newPasswordConfirm = content.getRequestParameterByName(RequestParam.NEW_PASSWORD_REPEAT_PARAM);
        String oldPassword = content.getRequestParameterByName(RequestParam.PASSWORD_PARAM);

        User user = (User)content.getSessionAttributeByName(SessionAtr.USER);
        Role userRole = user.getRole();

        String nextPath = (Role.ADMIN == userRole)? ADMIN_CHANGE_PASS_PATH : CLIENT_CHANGE_PASS_PATH;

        if (!ValidationUtil.isPasswordValid(newPassword)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.NEW_PASS_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isPasswordRepeatValid(newPassword, newPasswordConfirm)) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PASSWORD_REPEAT_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        String hashedPassword = HashPasswordUtil.sha1(oldPassword);
        if (!hashedPassword.equals(user.getPassword())) {
            content.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PASS_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        String hashedNewPassword = HashPasswordUtil.sha1(newPassword);
        userService.changePassword(user, hashedNewPassword);

        content.sessionInvalidate();
        content.setSessionAttributes(SessionAtr.USER, null);
        content.setSessionAttributes(SessionAtr.USER_TYPE, null);
        return new RequestResult(HOMEPAGE_PATH, NavigationType.FORWARD);
    }
}
