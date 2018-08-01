package com.epam.movierating.service;

import com.epam.movierating.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviewList(int filmId) throws ServiceException;
    Review addReview(Review review) throws ServiceException;
}
