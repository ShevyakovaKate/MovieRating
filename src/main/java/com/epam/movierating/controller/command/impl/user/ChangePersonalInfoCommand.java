package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.DateTimeConverter;
import com.epam.movierating.util.ValidationUtil;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

import java.time.LocalDate;

public class ChangePersonalInfoCommand implements Command {
    private static final String CLIENT_ACCOUNT_PATH = ConfigurationManager.getProperty(Paths.USER_PROFILE_PAGE);
    private static final String ADMIN_ACCOUNT_PATH = ConfigurationManager.getProperty(Paths.ADMIN_PROFILE_PAGE);
  /* private static final String GET_CLIENT_ACCOUNT_PATH = ConfigurationManager.getProperty(Paths.GET_CLIENT_ACCOUNT_PAGE);
    private static final String GET_ADMIN_ACCOUNT_PATH = ConfigurationManager.getProperty(Paths.GET_ADMIN_ACCOUNT_PAGE);*/

    private UserService userService;

    public ChangePersonalInfoCommand(UserService userService) {
        this.userService = userService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String email = requestContent.getRequestParameterByName(RequestParam.EMAIL_PARAM);

        String name = requestContent.getRequestParameterByName(RequestParam.NAME_PARAM);
        String date = requestContent.getRequestParameterByName(RequestParam.BIRTHDAY);
        String city = requestContent.getRequestParameterByName(RequestParam.CITY);
        String info = requestContent.getRequestParameterByName(RequestParam.INFO);

        User user = (User)requestContent.getSessionAttributeByName(SessionAtr.USER);
        String nextPath = (user.getRole() == Role.ADMIN)? ADMIN_ACCOUNT_PATH : CLIENT_ACCOUNT_PATH;

        if (!ValidationUtil.isEmailValid(email)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.EMAIL_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }


        String currentUserEmail = user.getEmail();
        boolean isCurrentUserEmail = currentUserEmail.equalsIgnoreCase(email);
        if (userService.isEmailExist(email) && !isCurrentUserEmail) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.EXIST_EMAIL_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isNameValid(name)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.NAME_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isDateValid(date)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PASSPORT_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        LocalDate birthday = DateTimeConverter.convertStringToDate(date);

        if (!ValidationUtil.isCityValid(city)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PHONE_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isInfoValid(info)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.PHONE_ERROR);
            return new RequestResult(nextPath, NavigationType.FORWARD);
        }

        user.setName(name);
        user.setEmail(email);
        user.setBirthday(birthday);
        user.setCity(city);
        user.setInfo(info);

        userService.editUserInfo(user);
        requestContent.setSessionAttributes(SessionAtr.USER, user);
        requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_POSITIVE, Messages.EDIT_USER_SUCCESS);

        return new RequestResult(nextPath, NavigationType.FORWARD);
    }
}
