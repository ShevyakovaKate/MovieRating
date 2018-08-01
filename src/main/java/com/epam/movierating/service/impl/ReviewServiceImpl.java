package com.epam.movierating.service.impl;

import com.epam.movierating.dao.*;
import com.epam.movierating.dao.impl.GenreDaoImpl;
import com.epam.movierating.dao.impl.ReviewDaoImpl;
import com.epam.movierating.dao.pool.ConnectionPool;
import com.epam.movierating.dao.pool.ConnectionPoolException;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.Review;
import com.epam.movierating.service.ReviewService;
import com.epam.movierating.service.ServiceException;

import java.sql.Connection;
import java.util.List;

public class ReviewServiceImpl implements ReviewService {
    private ReviewDao reviewDao;

    public ReviewServiceImpl() {
        this.reviewDao = new ReviewDaoImpl();
    }

    @Override
    public List<Review> getReviewList(int filmId) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(reviewDao);

        List<Review> reviews;
        try {
            reviews = reviewDao.getReviewList(filmId);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Problem while getting film", e);
        } finally {
            helper.endTransaction();
        }

        return reviews;
    }

    @Override
    public Review addReview(Review review) throws ServiceException {
        TransactionHelper helper = new TransactionHelper();
        helper.beginTransaction(reviewDao);

        Review finalReview;

        try {
            finalReview = reviewDao.insertReview(review);
            helper.commit();
        } catch (DaoException e) {
            helper.rollback();
            throw new ServiceException("Proplem while adding review", e);
        } finally {
            helper.endTransaction();
        }
        return finalReview;
    }
}
