package com.epam.movierating.service.impl;

import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.GenreDao;
import com.epam.movierating.dao.TransactionHelper;
import com.epam.movierating.dao.impl.GenreDaoImpl;
import com.epam.movierating.entity.Genre;
import com.epam.movierating.service.GenreService;
import com.epam.movierating.service.ServiceException;

import java.util.ArrayList;
import java.util.List;

public class GenreServiceImpl implements GenreService {
    private GenreDao genreDao;

    public GenreServiceImpl() {
        this.genreDao = new GenreDaoImpl();
    }

    @Override
    public List<Genre> getGenresList() throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(genreDao);

        List<Genre> genres;
        try {
            genres = genreDao.getAll();
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem with getting list of genres :", e);
        } finally {
            helper.endTransaction();
        }
        return genres;
    }

    @Override
    public List<Genre> getGenresByFilmId(int filmId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(genreDao);

        List<Genre> genres;
        try {
            genres = genreDao.getGenresByFilmId(filmId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem with getting list of genres :", e);
        } finally {
            helper.endTransaction();
        }
        return genres;
    }
}
