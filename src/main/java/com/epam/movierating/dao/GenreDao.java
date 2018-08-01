package com.epam.movierating.dao;

import com.epam.movierating.entity.Genre;

import java.util.List;

public interface GenreDao extends GenericDao {
    List<Genre> getAll() throws DaoException;
    List<Genre> getGenresByFilmId(int filmId) throws DaoException;
}
