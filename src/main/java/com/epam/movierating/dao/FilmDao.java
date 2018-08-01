package com.epam.movierating.dao;

import com.epam.movierating.entity.Film;

import java.util.List;
import java.util.Map;

public interface FilmDao extends GenericDao {
    int countAllFilms() throws DaoException;
    List<Film> getFilmsByRaiting(int from, int limit) throws DaoException;
    List<Film> getFilmsByYear(int year, int from, int limit) throws DaoException;
    int countFilmsByYear(int year) throws DaoException;
    Film getFilm(int filmId) throws DaoException;

    List<Film> searchFilms(Map<String, String> searchParams, int from, int limit) throws DaoException;
    int countSearchFilms(Map<String, String> searchParams) throws DaoException;

    void updateRating(int filmId) throws DaoException;
    double getFilmRating(int filmId) throws DaoException;
    //TODO should be in userDao
    List<Integer> getBookmarks(int userId, int from, int limit) throws DaoException;
    int countBookmarks(int userId) throws DaoException;

    Film insertFilm(Film film) throws DaoException;
    void deleteFilm(int filmId) throws DaoException;
}
