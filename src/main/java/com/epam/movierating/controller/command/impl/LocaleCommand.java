package com.epam.movierating.controller.command.impl;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;

public class LocaleCommand implements Command {
    private static final String EN_LOCALE_MARKER = "EN";
    private static final String EN_LOCALE_ATR = "en_US";
    private static final String RU_LOCALE_ATR = "ru_RU";
    private static final String LOCALE_PARAM = "locale";

    private static final String HOMEPAGE = ConfigurationManager.getProperty(Paths.HOMEPAGE);
    private static final String USER_HOMEPAGE = ConfigurationManager.getProperty(Paths.USER_HOME_PAGE);

    private static final String ADMIN_HOMEPAGE = ConfigurationManager.getProperty(Paths.ADMIN_HOME_PAGE);


    public LocaleCommand() {
    }

    @Override
    public RequestResult execute(RequestContent content) {
        String requestLocale = content.getRequestParameterByName(LOCALE_PARAM);
        String locale = chooseLocale(requestLocale);
        content.setSessionAttributes(LOCALE_PARAM, locale);

        User user = (User)content.getSessionAttributeByName(SessionAtr.USER);

        if (user == null) {
            return new RequestResult(HOMEPAGE,NavigationType.REDIRECT);
        }

        Role userRole = user.getRole();
        if(userRole == Role.USER) {
            return new RequestResult(USER_HOMEPAGE , NavigationType.REDIRECT);
        }

        return new RequestResult(ADMIN_HOMEPAGE, NavigationType.REDIRECT);

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
