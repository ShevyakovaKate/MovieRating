package com.epam.movierating.service.impl;

        import com.epam.movierating.dao.DaoException;
        import com.epam.movierating.dao.FilmDao;
        import com.epam.movierating.dao.TransactionHelper;
        import com.epam.movierating.dao.impl.FilmDaoImpl;
        import com.epam.movierating.entity.Film;
        import com.epam.movierating.service.FilmService;
        import com.epam.movierating.service.ServiceException;
        import com.epam.movierating.util.constant.RequestParam;

        import java.util.ArrayList;
        import java.util.List;
        import java.util.Map;

public class FilmServiceImpl implements FilmService {
    private FilmDao filmDao;
    //TODO CREATE DAO OBJECT in every method

    public FilmServiceImpl() {
        this.filmDao = new FilmDaoImpl();
    }
    /*@Override
    public List<Film> getFilmListByGenre(int genreId, int from, int limit) throws ServiceException{
        TransactionHelper helper = new TransactionHelper();

        helper.beginTransaction(filmDao);

        List<Film> filmList;
        try {
            filmList = filmDao.getFilmsByGenre(genreId, from, limit);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmList;
    }

    @Override
    public int countFilmsByGenre(int genreId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        int filmCount;
        try {
            filmCount = filmDao.countFilmsByGenre(genreId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmCount;
    }*/

    @Override
    public int countAllFilms() throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        int filmCount;
        try {
            filmCount = filmDao.countAllFilms();
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmCount;
    }

    @Override
    public List<Film> getFilmListByRaiting(int from, int limit) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();

        helper.beginTransaction(filmDao);

        List<Film> filmList;
        try {
            filmList = filmDao.getFilmsByRaiting(from, limit);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmList;
    }

    @Override
    public Film getFilm(int filmId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        Film film;
        try {
            film = filmDao.getFilm(filmId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film", e);
        } finally {
            helper.endTransaction();
        }

        return film;
    }

    @Override
    public List<Film> getFimListByYear(int year, int from, int limit) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        List<Film> filmList;
        try {
            filmList = filmDao.getFilmsByYear(year, from, limit);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmList;
    }

    @Override
    public int countFilmsByYear(int year) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        int filmCount;
        try {
            filmCount = filmDao.countFilmsByYear(year);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }

        return filmCount;
    }

    @Override
    public List<Film> searchFilms(Map<String, String> searchParam, int from, int limit) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        List<Film> films;
        try {

            films = filmDao.searchFilms(searchParam, from, limit);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }
        return films;
    }

    @Override
    public int countSearchingFilms(Map<String, String> searchParam) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        int filmCount;
        try {
            filmCount = filmDao.countSearchFilms(searchParam);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }
        return filmCount;
    }

    @Override
    public double updateRating(int filmId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        double newRating;
        try {
            filmDao.updateRating(filmId);
            newRating = filmDao.getFilmRating(filmId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film list", e);
        } finally {
            helper.endTransaction();
        }
        return newRating;
    }

    @Override
    public int countBookmarks(int userId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        int numberOfBookmarks;
        try {
            numberOfBookmarks = filmDao.countBookmarks(userId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during update user profile info:", e);
        } finally {
            helper.endTransaction();
        }
        return numberOfBookmarks;
    }

    @Override
    public List<Film> getBookmarks(int userId, int from, int limit) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        List<Film> bookmarks = new ArrayList<>();
        List<Integer> bookmarksId;
        try {
            bookmarksId = filmDao.getBookmarks(userId, from, limit);
            for (Integer bookmarkId: bookmarksId) {
                Film film = filmDao.getFilm(bookmarkId);
                bookmarks.add(film);
            }
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during update user profile info:", e);
        } finally {
            helper.endTransaction();
        }
        return bookmarks;
    }

    @Override
    public boolean addFilm(Film film) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        Film finalFilm;
        try {
            finalFilm = filmDao.insertFilm(film);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during adding new film:", e);
        } finally {
            helper.endTransaction();
        }
        return finalFilm != null;
    }

    @Override
    public void deleteFilm(int filmId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(filmDao);

        try {
           filmDao.deleteFilm(filmId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem during adding new film:", e);
        } finally {
            helper.endTransaction();
        }
    }
}
