package com.epam.movierating.command;

import com.epam.movierating.constant.LevelAccess;

public enum CommandType {
    /*GUEST COMMANDS*/
    LOGIN("LOGIN", LevelAccess.GUEST),
    SIGN_UP("SIGN_UP", LevelAccess.GUEST),

    /*ANY COMMANDS*/
    LOCALE("LOCALE", LevelAccess.COMMON),
    GETPAGE("GETPAGE", LevelAccess.COMMON);

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
