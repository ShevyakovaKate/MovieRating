package com.epam.movierating.service;

import com.epam.movierating.entity.User;

public interface UserService {
    User login(String enterEmail, String enterPasssword) throws ServiceException;
    boolean signUp(User user) throws ServiceException;
    void changePassword(User user, String newPassword) throws ServiceException;
    boolean isEmailExist(String enterEmail) throws ServiceException;
    void editUserInfo(User user) throws ServiceException;
    void addBookmark(int filmId, int userId) throws ServiceException;
    void deleteBookmark(int filmId, int userId) throws ServiceException;

}
