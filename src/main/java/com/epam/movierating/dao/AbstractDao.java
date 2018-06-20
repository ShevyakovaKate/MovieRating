package com.epam.movierating.dao;

import java.sql.Connection;

public abstract class AbstractDao {
    protected Connection connection;

    void setConnection(Connection connection) {
        this.connection = connection;
    }
}
