package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.AbstractDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.UserDao;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.pool.ConnectionPool;
import com.epam.movierating.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";
    private static final String INSERT_USER = "INSERT INTO users (email,password,name,role_id) VALUES(?, ?, ?, ?)";

    @Override
    public List<User> findAllUsers() throws DaoException {
        List<User> users = new ArrayList<>();
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = ResultSetConverter.createUserEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find all users", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Exception due to connecting to the database.", e);
        }
        return users;
    }

    @Override
    public User getUserByEmailPassword(String email, String password) throws DaoException {
        User user = null;
        try {
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = ResultSetConverter.createUserEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find all users", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Exception due to connecting to the database.", e);
        }
        return user;
    }

    @Override
    public User create(User user) throws DaoException {
        Integer autoIncrementedUserId;
        try{
            Connection connection = ConnectionPool.getInstance().takeConnection();
            PreparedStatement statement = connection.prepareStatement(INSERT_USER, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getName());
            statement.setString(4, Role.USER.getDbId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            autoIncrementedUserId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Fail when add user ", e);
        } catch (ConnectionPoolException e) {
            throw new DaoException("Exception due to connecting to the database.", e);
        }
        user.setId(autoIncrementedUserId);
        return user;
    }
}
