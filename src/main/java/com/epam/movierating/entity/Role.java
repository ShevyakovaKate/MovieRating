package com.epam.movierating.entity;

public enum Role {
    USER, ADMIN;

    public String getDbId(){
        return ADMIN.equals(this) ? "1" : "2";
    }
}
