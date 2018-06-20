package com.epam.movierating.service;

import com.epam.movierating.entity.User;

public interface UserService {
    User login(String enterEmail, String enterPasssword) throws ServiceException;

    boolean signUp(User user) throws ServiceException;
}
