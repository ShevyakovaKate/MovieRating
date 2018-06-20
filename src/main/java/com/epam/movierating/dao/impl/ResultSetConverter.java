package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ResultSetConverter {
    private static final String ID_PARAM = "id_user";
    private static final String PASSWORD_PARAM = "password";
    private static final String LOGIN_PARAM = "email";
    private static final String NAME_PARAM = "name";
    private static final String RAITING_PARAM = "raiting";
    private static final String ROLE_PARAM = "role_id";

    private static final int ADMIN_ID = 1;


    public static User createUserEntity(ResultSet resultSet) throws DaoException {
        User user;
        try {
            int userId = resultSet.getInt(ID_PARAM);
            String password = resultSet.getString(PASSWORD_PARAM);
            String email = resultSet.getString(LOGIN_PARAM);
            String name = resultSet.getString(NAME_PARAM);
            double raiting = resultSet.getDouble(RAITING_PARAM);
            int roleId = resultSet.getInt(ROLE_PARAM);
            Role role = (roleId == ADMIN_ID ? Role.ADMIN : Role.USER);

            user = new User();

            user.setId(userId);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setRaiting(raiting);
            user.setRole(role);

        } catch (SQLException e) {
            throw new DaoException("Exception during creating User ", e);
        }
        return user;
    }
}
