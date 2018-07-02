package com.epam.movierating.command.impl;

import com.epam.movierating.command.Command;
import com.epam.movierating.constant.SessionAtr;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;

import static com.epam.movierating.constant.RequestParam.*;

public class LocaleCommand implements Command {
    private static final String EN_LOCALE_MARKER = "EN";
    private static final String EN_LOCALE_ATR = "en_US";
    private static final String RU_LOCALE_ATR = "ru_RU";
    private static final String LOCALE_PARAM = "locale";

    public LocaleCommand() {
    }

    @Override
    public RequestResult execute(RequestContent content) {
        String requestLocale = content.getRequestParameterByName(LOCALE_PARAM);
        String locale = chooseLocale(requestLocale);
        content.setSessionAttributes(LOCALE_PARAM, locale);

        User user = (User)content.getSessionAttributeByName(SessionAtr.USER);

        if (user == null) {
            return new RequestResult("jsp/homepage.jsp",NavigationType.FORWARD);
        }

        Role userRole = user.getRole();
        if(userRole == Role.USER) {
            return new RequestResult("jsp/user/user-home.jsp" , NavigationType.FORWARD);
        }

        return new RequestResult("jsp/admin/admin-home.jsp", NavigationType.FORWARD);

    }

    private String chooseLocale(String localeParameter) {
        String locale;
        switch (localeParameter) {
            case EN_LOCALE_MARKER:
                locale = EN_LOCALE_ATR;
                break;
            default:
                locale = RU_LOCALE_ATR;
                break;
        }
        return locale;
    }
}
