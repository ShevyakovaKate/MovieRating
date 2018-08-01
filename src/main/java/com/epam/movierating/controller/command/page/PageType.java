package com.epam.movierating.controller.command.page;

import com.epam.movierating.util.constant.LevelAccess;

public enum PageType {
    //GUEST pages
    HOMEPAGE_JSP("HOMEPAGE_JSP", LevelAccess.GUEST),
    MOVIE_LIST_PAGE("OVIE_LIST_PAGE", LevelAccess.GUEST),

    //COMMON pages
    NOT_FOUND_PAGE_JSP("NOT_FOUND_PAGE_JSP", LevelAccess.COMMON),

    //USER pages
    USERHOME_JSP("USERHOME_JSP", LevelAccess.USER),


    //ADMIN pages
    ADMINHOME_JSP("ADMINHOME_JSP", LevelAccess.ADMIN);

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
