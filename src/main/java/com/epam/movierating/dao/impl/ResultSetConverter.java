package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.entity.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.sql.Date;

public class ResultSetConverter {
    private static final String ID_PARAM = "id_user";
    private static final String PASSWORD_PARAM = "password";
    private static final String EMAIL = "email";
    private static final String NAME_PARAM = "name";
    private static final String RAITING_PARAM = "raiting";
    private static final String ROLE_PARAM = "role_id";
    private static final String BIRTHDAY = "birthday";
    private static final String CITY = "city";
    private static final String INFO = "info";

    private static final int ADMIN_ID = 1;

    private static final String FILM_ID = "id";
    private static final String FILM_NAME = "name";
    private static final String FILM_RAITING = "rating";
    private static final String YEAR_OF_ISSUE = "year_of_issue";
    private static final String DIRECTOR = "director";
    private static final String DESCRIPTION = "description";

    private static final String GENRE_ID = "id";
    private static final String GENRE_NAME= "genre_name";

    private static final String REVIEW_ID = "id_comment";
    private static final String REVIEW_CONTENT = "content";
    private static final String REVIEW_ID_USER = "id_user";
    private static final String REVIEW_USER_NAME = "name";
    private static final String REVIEW_ID_FILM = "id_film";
    private static final String REVIEW_RATING = "rating";


    public static User createUserEntity(ResultSet resultSet) throws DaoException {
        User user;
        try {
            int userId = resultSet.getInt(ID_PARAM);
            String password = resultSet.getString(PASSWORD_PARAM);
            String email = resultSet.getString(EMAIL);
            String name = resultSet.getString(NAME_PARAM);
            Date date = resultSet.getDate(BIRTHDAY);
            LocalDate birthday = null;
            if (date != null) {
               birthday = date.toLocalDate();
            }
            double raiting = resultSet.getDouble(RAITING_PARAM);
            String city = resultSet.getString(CITY);
            String info = resultSet.getString(INFO);
            int roleId = resultSet.getInt(ROLE_PARAM);

            Role role = (roleId == ADMIN_ID ? Role.ADMIN : Role.USER);

            user = new User();

            user.setId(userId);
            user.setPassword(password);
            user.setEmail(email);
            user.setName(name);
            user.setRaiting(raiting);
            if (birthday != null) {
                user.setBirthday(birthday);
            }
            user.setCity(city);
            user.setInfo(info);
            user.setRole(role);

        } catch (SQLException e) {
            throw new DaoException("Exception during creating User ", e);
        }
        return user;
    }

    public static Film createFilmEntity(ResultSet resultSet) throws DaoException {
        Film film;
        try {
            int filmId = resultSet.getInt(FILM_ID);
            String name = resultSet.getString(FILM_NAME);
            double raiting = resultSet.getDouble(FILM_RAITING);
            int yearOfIssue = resultSet.getInt(YEAR_OF_ISSUE);
            String director = resultSet.getString(DIRECTOR);
            String description = resultSet.getString(DESCRIPTION);

            film = new Film();

            film.setId(filmId);
            film.setName(name);
            film.setRaiting(raiting);
            film.setYearOfIssue(yearOfIssue);
            film.setDirector(director);
            film.setDescription(description);
        } catch (SQLException e) {
            throw new DaoException("Exception during creating film", e);
        }
        return film;
    }

    public static Genre createGenreEntity(ResultSet resultSet) throws DaoException {
        Genre genre;
        try {
            int genreId = resultSet.getInt(GENRE_ID);
            String name = resultSet.getString(GENRE_NAME);

            genre = new Genre();

            genre.setId(genreId);
            genre.setName(name);
        } catch (SQLException e) {
            throw new DaoException("Exception during creating genre", e);
        }
        return genre;
    }

    public static Review createReviewEntity(ResultSet resultSet) throws DaoException {
        Review review;
        try {
            int reviewId = resultSet.getInt(REVIEW_ID);
            String reviewContent = resultSet.getString(REVIEW_CONTENT);
            int userId = resultSet.getInt(REVIEW_ID_USER);
            String userName = resultSet.getString(REVIEW_USER_NAME);
            int filmId = resultSet.getInt(REVIEW_ID_FILM);
            int rating = resultSet.getInt(REVIEW_RATING);

            review = new Review();

            review.setId(reviewId);
            review.setContent(reviewContent);
            review.setUserId(userId);
            review.setUserName(userName);
            review.setFilmId(filmId);
            review.setRating(rating);
        } catch (SQLException e) {
            throw new DaoException("Exception during creating review", e);
        }
        return review;
    }

}
