package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.AbstractDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.UserDao;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.dao.pool.ConnectionPool;
import com.epam.movierating.dao.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl extends AbstractDao implements UserDao {
    private static final String FIND_ALL_USERS = "SELECT * FROM users";
    private static final String FIND_USER_BY_EMAIL_PASSWORD = "SELECT * FROM users WHERE email=? AND password=?";
    private static final String INSERT_USER = "INSERT INTO users (email,password,name,role_id) VALUES(?, ?, ?, ?)";
    private static final String UPDATE_USER_PASSWORD = "UPDATE users SET password=? WHERE id_user=?";
    private static final String FIND_USER_BY_EMAIL = "SELECT * FROM users WHERE email=?";
    private static final String UPDATE_USER_INFO = "UPDATE users SET name=? ,birthday=? ,email=?, city=?, info=? WHERE id_user=?";
    private static final String INSERT_BOOKMARK = "INSERT INTO bookmarks (user_id, film_id) values (?, ?)";
    private static final String DELETE_BOOKMARK = "DELETE FROM bookmarks WHERE film_id=? AND user_id=?";


    @Override
    public List<User> findAllUsers() throws DaoException {
        //TODO rewrite
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_ALL_USERS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                User user = ResultSetConverter.createUserEntity(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find all users", e);
        }
        return users;
    }

    @Override
    public User getUserByEmailPassword(String email, String password) throws DaoException {
        User user = null;
        try {
            PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL_PASSWORD);
            statement.setString(1, email);
            statement.setString(2, password);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                user = ResultSetConverter.createUserEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find all users", e);
        }
        return user;
    }

    @Override
    public User create(User user) throws DaoException {
        Integer autoIncrementedUserId;
        try {
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
        }
        user.setId(autoIncrementedUserId);
        return user;
    }

    @Override
    public void updatePassword(Integer id, String password) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_PASSWORD)) {
            statement.setString(1, password);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fail when update user password by id", e);
        }
    }

    @Override
    public User getByEmail(String email) throws DaoException {
        User user = null;
        try (PreparedStatement statement = connection.prepareStatement(FIND_USER_BY_EMAIL)) {
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = ResultSetConverter.createUserEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Fail when find user by email " + email + " ", e);
        }
        return user;
    }

    @Override
    public void updateUser(User user) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(UPDATE_USER_INFO)) {
            statement.setString(1, user.getName());
            Date sqlDate = Date.valueOf(user.getBirthday());
            statement.setDate(2, sqlDate);
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getCity());
            statement.setString(5, user.getInfo());
            statement.setInt(6, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fail when update user info", e);
        }
    }

    @Override
    public void insertBookmark(int filmId, int userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(INSERT_BOOKMARK)) {
            statement.setInt(1, userId);
            statement.setInt(2, filmId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fail when inserting bookmark", e);
        }
    }

    @Override
    public void deleteBookmark(int filmId, int userId) throws DaoException {
        try (PreparedStatement statement = connection.prepareStatement(DELETE_BOOKMARK)) {
            statement.setInt(1, filmId);
            statement.setInt(2, userId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Fail when deleting bookmark", e);
        }
    }
}
