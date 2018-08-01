package com.epam.movierating.dao;

import com.epam.movierating.entity.User;

import java.util.List;

public interface UserDao extends GenericDao {
    List<User> findAllUsers() throws DaoException;
    User getUserByEmailPassword(String email, String password) throws DaoException;
    User create(User user) throws DaoException;
    void updatePassword(Integer id, String password) throws DaoException;
    User getByEmail(String eMail) throws DaoException;
    void updateUser(User user) throws DaoException;
    void insertBookmark(int filmId, int userId) throws DaoException;
    void deleteBookmark(int filmId, int userId) throws DaoException;

}
