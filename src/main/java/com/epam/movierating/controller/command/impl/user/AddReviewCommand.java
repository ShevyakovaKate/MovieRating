package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.Review;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.*;
import com.epam.movierating.util.HashPasswordUtil;
import com.epam.movierating.util.RatingCalculator;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

public class AddReviewCommand implements Command {

    private static final String FILM_PAGE = ConfigurationManager.getProperty(Paths.FILM_COMMAND);


    private ReviewService reviewService;
    private UserService userService;
    private FilmService filmService;

    public AddReviewCommand(ReviewService reviewService, UserService userService, FilmService filmService) {

        this.reviewService = reviewService;
        this.userService = userService;
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String content = requestContent.getRequestParameterByName(RequestParam.CONTENT_PARAM);

        String ratingParam = requestContent.getRequestParameterByName(RequestParam.RAITING_PARAM);
        int rating = Integer.parseInt(ratingParam);

        Film film = (Film) requestContent.getSessionAttributeByName(SessionAtr.FILM);
        int filmId = film.getId();

        User user = (User) requestContent.getSessionAttributeByName(SessionAtr.USER);
        int userId = user.getId();

        Review review = new Review();
        review.setContent(content);
        review.setRating(rating);
        review.setFilmId(filmId);
        review.setUserId(userId);

        Review finalReview = reviewService.addReview(review);

        if (finalReview == null) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.ADD_REVIEW_ERROR);
            return new RequestResult(FILM_PAGE + filmId, NavigationType.REDIRECT);
        }

        double userRating = user.getRaiting();
        double filmRating = film.getRaiting();

        double newUserRating = RatingCalculator.culcUserRatingByAddingReview(userRating, filmRating, rating);
        user.setRaiting(newUserRating);

        userService.editUserInfo(user);
        requestContent.setSessionAttributes(SessionAtr.USER, user);

        double newFilmRating = filmService.updateRating(filmId);
        film.setRaiting(newFilmRating);
        requestContent.setSessionAttributes(SessionAtr.USER, user);

        return new RequestResult(FILM_PAGE + filmId, NavigationType.FORWARD);

    }

}
