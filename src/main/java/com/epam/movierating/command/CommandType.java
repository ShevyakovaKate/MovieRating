package com.epam.movierating.command;

public enum CommandType {
    LOGIN("LOGIN"),
    SIGN_UP("SIGN_UP"),
    LOCALE("LOCALE");

    private final String name;

    CommandType(String name) {
        this.name = name;
    }

}
