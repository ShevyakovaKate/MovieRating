package com.epam.movierating.service;

import com.epam.movierating.entity.Film;

import javax.xml.ws.Service;
import java.util.List;
import java.util.Map;

public interface FilmService {
    int countAllFilms() throws ServiceException;
    int countFilmsByYear(int year) throws ServiceException;
    List<Film> getFilmListByRaiting(int from, int limit) throws ServiceException;
    List<Film> getFimListByYear(int year, int from, int limit) throws ServiceException;
    Film getFilm(int filmId) throws ServiceException;

    List<Film> searchFilms(Map<String, String> searchParam, int from, int limit) throws ServiceException;
    int countSearchingFilms(Map<String, String> searchParam) throws ServiceException;

    double updateRating(int filmId) throws ServiceException;

    int countBookmarks(int userId) throws ServiceException;
    List<Film> getBookmarks(int userId, int from, int limit) throws ServiceException;

    boolean addFilm(Film film) throws ServiceException;

    void deleteFilm(int filmId) throws ServiceException;

}
