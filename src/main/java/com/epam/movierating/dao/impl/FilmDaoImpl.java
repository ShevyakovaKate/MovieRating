package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.AbstractDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.FilmDao;
import com.epam.movierating.entity.Film;
import com.epam.movierating.util.constant.RequestParam;
import sun.misc.Request;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FilmDaoImpl extends AbstractDao implements FilmDao {
    //TODO use find all
    private static final String GET_FILMS = "SELECT * FROM films limit ?,?";
    private static final String GET_FILM = "SELECT * FROM films WHERE films.id = ?";
    private static final String GET_ALL_FILMS = "SELECT * FROM films";

    //TODO BASE_SQL_STRING and append "where strings" with StringJoiner Java 1.8
    private static final String GET_FILMS_BY_RAITING = "SELECT * FROM films ORDER BY rating DESC limit ?, ?";
    private static final String GET_FILMS_BY_YEAR_LIMIT = "SELECT * FROM films WHERE year_of_issue=? limit ?, ?";
    private static final String GET_FILMS_BY_YEAR ="SELECT * FROM films WHERE year_of_issue=?";
    private static final String UPDATE_FILM_RATING = "UPDATE films SET rating = (SELECT avg(rating) FROM comments WHERE id_film=? ) WHERE id=?";

    ///Needed SQL

    private static final String GET_FILMS_SQL_BASIC_LIMIT = "SELECT distinct films.id, films.name, films.rating, year_of_issue, director, description\n" +
            "FROM films INNER JOIN film_genres INNER JOIN film_has_genres\n" +
            "ON film_has_genres.films_id = films.id and\n" +
            "film_has_genres.film_genres_id = film_genres.id            \n" +
            "WHERE UPPER(films.name) LIKE UPPER(?)\n" +
            "limit ?, ?";

    private static final String GET_FILMS_SQL_BY_GENRES_LIMIT = "SELECT distinct films.id, films.name, films.rating, year_of_issue, director, description\n" +
            "FROM films INNER JOIN film_genres INNER JOIN film_has_genres\n" +
            "ON film_has_genres.films_id = films.id and\n" +
            "film_has_genres.film_genres_id = film_genres.id\n" +
            "WHERE UPPER(films.name) LIKE UPPER(?)" +
            "AND film_genres.id=?\n" +
            "limit ?, ?";

    private static final String GET_FILMS_SQL_BASIC = "SELECT distinct films.id, films.name, films.rating, year_of_issue, director, description\n" +
            "FROM films INNER JOIN film_genres INNER JOIN film_has_genres\n" +
            "ON film_has_genres.films_id = films.id and\n" +
            "film_has_genres.film_genres_id = film_genres.id\n" +
            "WHERE UPPER(films.name) LIKE UPPER(?)";

    private static final String GET_FILMS_SQL_BY_GENRES = "SELECT distinct films.id, films.name, films.rating, year_of_issue, director, description\n" +
            "FROM films INNER JOIN film_genres INNER JOIN film_has_genres\n" +
            "ON film_has_genres.films_id = films.id and\n" +
            "film_has_genres.film_genres_id = film_genres.id            \n" +
            "WHERE UPPER(films.name) LIKE UPPER(?)\n" +
            "AND film_genres.id=?\n";

    private static final String COUNT_BOOKMARKS_SQL = "SELECT * FROM bookmarks WHERE user_id=?";
    private static final String GET_BOOKMARKS_SQL = "SELECT * FROM bookmarks WHERE user_id=? limit ?, ?";
    private static final String INSERT_FILM = "INSERT INTO films (films.name, year_of_issue, director, description) " +
            "values (?, ?, ?, ?)";
    private static final String DELETE_FILM = "DELETE FROM films WHERE films.id=?";

    private static final String FILM_ID = "film_id";
    private static final String RATING = "rating";

    @Override
    public int countAllFilms() throws DaoException {
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_ALL_FILMS);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return count;
    }

    @Override
    public List<Film> getFilmsByRaiting(int from, int limit) throws DaoException {
        List<Film> films = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILMS_BY_RAITING);
            statement.setInt(1, from);
            statement.setInt(2, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = ResultSetConverter.createFilmEntity(resultSet);
                films.add(film);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return films;
    }

    @Override
    public Film getFilm(int filmId) throws DaoException {
        Film film = null;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILM);
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                film = ResultSetConverter.createFilmEntity(resultSet);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return film;
    }

    @Override
    public List<Film> getFilmsByYear(int year, int from, int limit) throws DaoException {
        List<Film> films = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILMS_BY_YEAR_LIMIT);
            statement.setInt(1, year);
            statement.setInt(2, from);
            statement.setInt(3, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = ResultSetConverter.createFilmEntity(resultSet);
                films.add(film);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return films;
    }

    @Override
    public int countFilmsByYear(int year) throws DaoException {
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILMS_BY_YEAR);
            statement.setInt(1, year);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return count;
    }

    @Override
    public List<Film> searchFilms(Map<String, String> searchParams, int from, int limit) throws DaoException {
        List<Film> films = new ArrayList<>();
        try {
            PreparedStatement statement = getSqlBySearchingParams(searchParams, from ,limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Film film = ResultSetConverter.createFilmEntity(resultSet);
                films.add(film);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return films;
    }

    @Override
    public int countSearchFilms(Map<String, String> searchParams) throws DaoException {
        int count = 0;
        try {
            PreparedStatement statement = getSqlBySearchingParams(searchParams);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during counting films", e);
        }
        return count;
    }

    private PreparedStatement getSqlBySearchingParams(Map<String, String> searchParams, int from, int limit) throws SQLException {
        PreparedStatement statement;
        if (searchParams.containsKey(RequestParam.GENRE_ID)) {
            statement = connection.prepareStatement(GET_FILMS_SQL_BY_GENRES_LIMIT);
            statement.setString(1, "%" + searchParams.get(RequestParam.SEARCHING_TEXT) + "%");
            int genreId = Integer.valueOf(searchParams.get(RequestParam.GENRE_ID));
            statement.setInt(2, genreId);
            statement.setInt(3, from);
            statement.setInt(4, limit);
        } else {
            statement = connection.prepareStatement(GET_FILMS_SQL_BASIC_LIMIT);

            statement.setString(1, "%" + searchParams.get(RequestParam.SEARCHING_TEXT) + "%");
            statement.setInt(2, from);
            statement.setInt(3, limit);
        }
        return statement;
    }

    private PreparedStatement getSqlBySearchingParams(Map<String, String> searchParams) throws SQLException {
        PreparedStatement statement;
        if (searchParams.containsKey(RequestParam.GENRE_ID)) {
            statement = connection.prepareStatement(GET_FILMS_SQL_BY_GENRES);
            statement.setString(1, "%" + searchParams.get(RequestParam.SEARCHING_TEXT) + "%");
            int genreId = Integer.valueOf(searchParams.get(RequestParam.GENRE_ID));
            statement.setInt(2, genreId);
        } else {
            statement = connection.prepareStatement(GET_FILMS_SQL_BASIC);
            statement.setString(1, "%" + searchParams.get(RequestParam.SEARCHING_TEXT) + "%");
        }
        return statement;
    }

    @Override
    public void updateRating(int filmId) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(UPDATE_FILM_RATING);
            statement.setInt(1, filmId);
            statement.setInt(2, filmId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
    }

    @Override
    public double getFilmRating(int filmId) throws DaoException {
        double filmRating = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(GET_FILM);
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while(resultSet.next()) {
                filmRating = resultSet.getDouble(RATING);
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return filmRating;
    }

    @Override
    public List<Integer> getBookmarks(int userId, int from, int limit) throws DaoException {
        List<Integer> bookmarksId = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_BOOKMARKS_SQL);
            statement.setInt(1, userId);
            statement.setInt(2, from);
            statement.setInt(3, limit);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                bookmarksId.add(resultSet.getInt(FILM_ID));
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find films", e);
        }
        return bookmarksId;
    }

    @Override
    public int countBookmarks(int userId) throws DaoException {
        int count = 0;
        try {
            PreparedStatement statement = connection.prepareStatement(COUNT_BOOKMARKS_SQL);
            statement.setInt(1, userId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                count++;
            }
        } catch (SQLException e) {
            throw new DaoException("Exception during find bookmarks", e);
        }
        return count;
    }

    @Override
    public Film insertFilm(Film film) throws DaoException {
        Integer autoIncrementedFilmId;

        try {
            PreparedStatement statement = connection.prepareStatement(INSERT_FILM, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, film.getName());
            statement.setInt(2, film.getYearOfIssue());
            statement.setString(3, film.getDirector());
            statement.setString(4, film.getDescription());
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            autoIncrementedFilmId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Exception during insert films", e);
        }
        film.setId(autoIncrementedFilmId);
        return film;
    }

    @Override
    public void deleteFilm(int filmId) throws DaoException {
        try {
            PreparedStatement statement = connection.prepareStatement(DELETE_FILM);
            statement.setInt(1, filmId);
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Exception during deleting film", e);
        }
    }
}
