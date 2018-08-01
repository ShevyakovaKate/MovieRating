package com.epam.movierating.controller.command.impl.page;

import com.epam.movierating.util.constant.LevelAccess;

import java.util.logging.Level;

public enum PageType {
    //GUEST pages
    HOMEPAGE_JSP("HOMEPAGE_JSP", LevelAccess.GUEST),
    MOVIE_LIST_PAGE("MOVIE_LIST_PAGE", LevelAccess.GUEST),
    FILM_JSP("FILM_JSP", LevelAccess.GUEST),
    INDEX_JSP("INDEX_JSP", LevelAccess.GUEST),

    //COMMON pages
    NOT_FOUND_PAGE_JSP("NOT_FOUND_PAGE_JSP", LevelAccess.COMMON),

    //USER pages
    USER_PROFILE_JSP("USER_PROFILE_JSP", LevelAccess.USER),
    USER_HOMEPAGE_JSP("USER_HOMEPAGE_JSP", LevelAccess.USER),
    CHANGE_PASSWORD_JSP("CHANGE_PASSWORD_JSP", LevelAccess.USER),
    CHANGE_INFO_JSP("CHANGE_INFO_JSP", LevelAccess.USER),

    //ADMIN pages
    ADMIN_MOVIE_LIST_JSP("ADMIN_MOVIE_LIST_JSP", LevelAccess.ADMIN),
    ADMIN_PROFILE_JSP("ADMIN_PROFILE_JSP", LevelAccess.ADMIN),
    ADD_FILM("ADD_FILM", LevelAccess.ADMIN);

    private final String name;
    private final String levelAccess;

    PageType(String name, String levelAccess) {
        this.name = name;
        this.levelAccess = levelAccess;
    }

    public String getLevelAccess() {
        return this.levelAccess;
    }

    @Override
    public String toString() {
        return name;
    }

    public static PageType fromString(String text) {
        for (PageType value : PageType.values()) {
            if (value.name.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }
}
