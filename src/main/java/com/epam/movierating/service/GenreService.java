package com.epam.movierating.service;

import com.epam.movierating.entity.Genre;

import java.util.List;

public interface GenreService {
    List<Genre> getGenresList() throws ServiceException;
    List<Genre> getGenresByFilmId(int filmId) throws ServiceException;
}
