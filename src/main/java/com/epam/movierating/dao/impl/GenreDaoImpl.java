package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.AbstractDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.GenreDao;
import com.epam.movierating.entity.Genre;
import com.epam.movierating.dao.pool.ConnectionPool;
import com.epam.movierating.dao.pool.ConnectionPoolException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreDaoImpl extends AbstractDao implements GenreDao  {
    private static final String GET_GENRES = "SELECT * FROM film_genres";

    private static final String GET_FILM_GENRES = "SELECT film_genres.id, genre_name\n" +
            "FROM films INNER JOIN film_genres INNER JOIN film_has_genres\n" +
            "ON film_has_genres.films_id = films.id and \n" +
            "film_has_genres.film_genres_id = film_genres.id \n" +
            "WHERE films.id=?";

    @Override
    public List<Genre> getAll() throws DaoException {
        List<Genre> genres = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_GENRES);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = ResultSetConverter.createGenreEntity(resultSet);
                genres.add(genre);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find genres", e);
        }
        return genres;
    }

    @Override
    public List<Genre> getGenresByFilmId(int filmId) throws DaoException {
        List<Genre> genres = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILM_GENRES);
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Genre genre = ResultSetConverter.createGenreEntity(resultSet);
                genres.add(genre);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find film genres", e);
        }
        return genres;
    }
}

