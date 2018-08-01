package com.epam.movierating.dao;

import com.epam.movierating.entity.Review;

import java.util.List;

public interface ReviewDao extends GenericDao {
    List<Review> getReviewList(int filmId) throws DaoException;
    Review insertReview(Review review) throws DaoException;
}
