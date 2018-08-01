package com.epam.movierating.dao;

import com.epam.movierating.dao.pool.ConnectionPool;
import com.epam.movierating.dao.pool.ConnectionPoolException;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHelper {
    /*private static final Logger logger = LogManager.getLogger(TransactionHelper.class);*/
    private Connection connection;

    public TransactionHelper(){}

    public void beginTransaction(GenericDao dao) {
        try {
            connection = ConnectionPool.getInstance().takeConnection();
            connection.setAutoCommit(false);
        } catch (ConnectionPoolException e) {
            /*logger.log(Level.ERROR, "Problem when take connection.");*/
            e.printStackTrace();
        } catch (SQLException e) {
            /*logger.log(Level.ERROR, "Problem when set auto commit false.");*/
            e.printStackTrace();
        }

        AbstractDao abstractDao = (AbstractDao)dao;
        abstractDao.setConnection(connection);
    }

    public void endTransaction() {
        try {
            connection.setAutoCommit(true);
            ConnectionPool.getInstance().releaseConnection(connection);
        } catch (SQLException|ConnectionPoolException e) {
            /*logger.log(Level.ERROR, "Problem when set auto commit true.");*/
            e.printStackTrace();
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            /*logger.log(Level.ERROR, "Transaction not commit.", e);*/
            e.printStackTrace();
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            /*logger.log(Level.ERROR, "Problem with roll bask transaction.", e);*/
            e.printStackTrace();
        }
    }
}
