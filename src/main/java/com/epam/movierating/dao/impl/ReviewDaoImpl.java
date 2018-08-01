package com.epam.movierating.dao.impl;

import com.epam.movierating.dao.AbstractDao;
import com.epam.movierating.dao.DaoException;
import com.epam.movierating.dao.ReviewDao;
import com.epam.movierating.dao.pool.ConnectionPool;
import com.epam.movierating.dao.pool.ConnectionPoolException;
import com.epam.movierating.entity.Genre;
import com.epam.movierating.entity.Review;
import com.epam.movierating.entity.Role;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ReviewDaoImpl extends AbstractDao implements ReviewDao {
    private static final String GET_REVIEWS_BY_FILM_ID = "SELECT comments.id_comment, comments.content, " +
            "comments.id_user, comments.id_film, comments.rating, users.name \n" +
            "FROM comments JOIN users ON comments.id_user=users.id_user \n" +
            "WHERE comments.id_film = ? \n" +
            "ORDER BY comments.date_time DESC";
    private static final String INSERT_REVIEW = "INSERT INTO comments (content, rating, id_user, id_film, date_time) " +
            "VALUES(?, ?, ?, ?, now())";

    @Override
    public List<Review> getReviewList(int filmId) throws DaoException {
        List<Review> reviews = new ArrayList<>();
        try {
            PreparedStatement statement = connection.prepareStatement(GET_REVIEWS_BY_FILM_ID);
            statement.setInt(1, filmId);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Review review = ResultSetConverter.createReviewEntity(resultSet);
                reviews.add(review);
            }

        } catch (SQLException e) {
            throw new DaoException("Exception during find reviews", e);
        }
        return reviews;
    }

    @Override
    public Review insertReview(Review review) throws DaoException {
        Integer autoIncrementedUserId;
        try{
            PreparedStatement statement = connection.prepareStatement(INSERT_REVIEW, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, review.getContent());
            statement.setInt(2, review.getRating());
            statement.setInt(3, review.getUserId());
            statement.setInt(4, review.getFilmId());

            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            rs.next();
            autoIncrementedUserId = rs.getInt(1);
        } catch (SQLException e) {
            throw new DaoException("Fail when insert review ", e);
        }

        review.setId(autoIncrementedUserId);
        return review;
    }
}
