package com.epam.movierating.service.impl;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.TransactionHelper;
import com.epam.movierating.dao.UserDao;
import com.epam.movierating.dao.impl.UserDaoImpl;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;

public class UserServiceImpl implements UserService {//в сервере тоже проверку vslidation делать
    private UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public User login(String enterEmail, String enterPasssword) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        User user;

        try {
            user = userDao.getUserByEmailPassword(enterEmail, enterPasssword);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Exceprion from Dao:", e);
        } finally {
            helper.endTransaction();
        }
        return user;
    }

    @Override
    public boolean signUp(User user) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        User resultUser;
        try {
            resultUser = userDao.create(user);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem when sign up Logic :", e);
        } finally {
            helper.endTransaction();
        }
        return resultUser != null;
    }

    @Override
    public void changePassword(User user, String newPassword) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        Integer userId = user.getId();
        try {
            userDao.updatePassword(userId, newPassword);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw  new ServiceException("Problem when update user password logic:", e);
        } finally {
            helper.endTransaction();
        }
    }

    @Override
    public boolean isEmailExist(String enterEmail) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        try {
            User user = userDao.getByEmail(enterEmail.toUpperCase());
            return user!= null;
        } catch (DaoException e) {
            throw new ServiceException("Problem when check email existance Logic :", e);
        } finally {
            helper.endTransaction();
        }
    }

    @Override
    public void editUserInfo(User user) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        try {
            userDao.updateUser(user);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during update user profile info:", e);
        } finally {
            helper.endTransaction();
        }
    }

    @Override
    public void addBookmark(int filmId, int userId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        try {
            userDao.insertBookmark(filmId, userId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during update user profile info:", e);
        } finally {
            helper.endTransaction();
        }
    }

    @Override
    public void deleteBookmark(int filmId, int userId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(userDao);

        try {
            userDao.deleteBookmark(filmId, userId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during update user profile info:", e);
        } finally {
            helper.endTransaction();
        }
    }
}
