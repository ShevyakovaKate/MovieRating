package com.epam.movierating.command.impl;

import com.epam.movierating.command.Command;
import com.epam.movierating.content.NavigationType;
import com.epam.movierating.content.RequestContent;
import com.epam.movierating.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;

public class LocaleCommand implements Command {
    private static final String EN_LOCALE_MARKER = "EN";
    private static final String EN_LOCALE_ATR = "en_US";
    private static final String RU_LOCALE_ATR = "ru_RU";
    public static final String USER = "user";

    private static final String LOCALE_PARAM = "locale";

    public LocaleCommand() {
    }

    @Override
    public RequestResult execute(RequestContent content) {
        String localeParameter = content.getRequestParameterByName(LOCALE_PARAM);

        String locale = chooseLocale(localeParameter);
        content.setSessionAttributes(LOCALE_PARAM, locale);

        return new RequestResult("jsp/login.jsp", NavigationType.FORWARD);
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
