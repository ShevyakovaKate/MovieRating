package com.epam.movierating.dao;

import com.epam.movierating.entity.User;

import java.util.List;

public interface UserDao {
    List<User> findAllUsers() throws DaoException;

    User getUserByEmailPassword(String email, String password) throws DaoException;

    User create(User user) throws DaoException;

}
