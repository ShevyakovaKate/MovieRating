package com.epam.movierating.controller.command;

import com.epam.movierating.util.constant.LevelAccess;

import java.util.logging.Level;

public enum CommandType {
    /*GUEST COMMANDS*/
    LOGIN("LOGIN", LevelAccess.GUEST),
    SIGN_UP("SIGN_UP", LevelAccess.GUEST),

    /*COMMON COMMANDS*/
    LOCALE("LOCALE", LevelAccess.COMMON),
    GET_PAGE("GET_PAGE", LevelAccess.COMMON),
    GET_FILMS_BY_GENRE("GET_FILMS_BY_GENRE", LevelAccess.COMMON),
    GET_FILMS_BY_RAITING("GET_FILMS_BY_RAITING", LevelAccess.COMMON),
    GET_FILMS_BY_YEAR("GET_FILMS_BY_YEAR", LevelAccess.COMMON),
    GET_FILM("GET_FILM", LevelAccess.COMMON),
    SEARCH_FILM("SEARCH_FILM", LevelAccess.COMMON),

    /*AUTHORIZED COMMANDS*/
    LOGOUT("LOGOUT", LevelAccess.AUTHORIZED),

    /*USER COMMANDS*/
    CHANGE_PASSWORD("CHANGE_PASSWORD", LevelAccess.USER),
    CHANGE_PERSONAL_INFO("CHANGE_PERSONAL_INFO", LevelAccess.USER),
    ADD_REVIEW("ADD_REVIEW", LevelAccess.USER),
    ADD_BOOKMARK("ADD_BOOKMARK", LevelAccess.USER),
    GET_BOOKMARKS("GET_BOOKMARKS", LevelAccess.USER),
    DELETE_BOOKMARK("DELETE_BOOKMARK", LevelAccess.USER),

    /*ADMIN COMMANDS*/
    ADD_FILM("ADD_FILM", LevelAccess.ADMIN),
    DELETE_FILM("DELETE_FILM", LevelAccess.ADMIN);


    private final String name;
    private final String levelAccess;

    CommandType(String name, String levelAccess) {
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

    public static CommandType fromString(String text) {
        for (CommandType value : CommandType.values()) {
            if (value.name.equalsIgnoreCase(text)) {
                return value;
            }
        }
        return null;
    }



}
