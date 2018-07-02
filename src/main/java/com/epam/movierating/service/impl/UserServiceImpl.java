package com.epam.movierating.service.impl;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.UserDao;
import com.epam.movierating.dao.impl.UserDaoImpl;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;

public class UserServiceImpl implements UserService {//в сервере тоже проверку vslidation делать
    @Override
    public User login(String enterEmail, String enterPasssword) throws ServiceException {
        UserDao userDao = new UserDaoImpl();
        try {
            return userDao.getUserByEmailPassword(enterEmail, enterPasssword);
        } catch (DaoException e) {
            throw new ServiceException("Exceprion from Dao:", e);
        }
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        UserDao userDao = new UserDaoImpl();//не создавать новый userdao

        User resultUser;
        try {
            resultUser = userDao.create(user);
        } catch (DaoException e) {
            throw new ServiceException("Problem when sign up Logic :", e);
        }
        return resultUser != null;
    }

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        User user = new User();
        user.setEmail("lsnkjd@kmdl");
        user.setPassword("9823hd");
        user.setName("kigv");
        try {
            boolean isOk = userService.signUp(user);
        } catch (ServiceException e) {
            e.printStackTrace();
        }
    }
}
