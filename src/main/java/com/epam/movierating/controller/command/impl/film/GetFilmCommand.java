package com.epam.movierating.controller.command.impl.film;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.FilmService;
import com.epam.movierating.service.GenreService;
import com.epam.movierating.service.ReviewService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

public class GetFilmCommand implements Command {

    private static final String FILM_PAGE = ConfigurationManager.getProperty(Paths.FILM_PAGE);
    private static final String USER_FILM_PAGE = ConfigurationManager.getProperty(Paths.USER_FILM_PAGE);

    private static final String ADMIN_FILM_PAGE = ConfigurationManager.getProperty(Paths.ADMIN_FILM_PAGE);
    private static final String ERROR_PAGE =  ConfigurationManager.getProperty(Paths.ERROR_PAGE);

    private FilmService filmService;
    private GenreService genreService;
    private ReviewService reviewService;

    public GetFilmCommand(FilmService filmService, GenreService genreService, ReviewService reviewService) {
        this.filmService = filmService;
        this.genreService = genreService;
        this.reviewService = reviewService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String filmIdParam = requestContent.getRequestParameterByName(RequestParam.FILM_ID);
        int filmId = Integer.parseInt(filmIdParam);

        Film film = filmService.getFilm(filmId);

        if (film == null) {
            return new RequestResult(ERROR_PAGE, NavigationType.FORWARD);
        }

        film.setGenres(genreService.getGenresByFilmId(film.getId()));
        requestContent.setRequestAttribute(SessionAtr.REVIEW_LIST, reviewService.getReviewList(film.getId()));
        requestContent.setRequestAttribute(SessionAtr.FILM, film);

        User user = (User)requestContent.getSessionAttributeByName(SessionAtr.USER);

        if(user == null) {
            return new RequestResult(FILM_PAGE, NavigationType.FORWARD);
        }

        Role userRole = user.getRole();
        if(userRole == Role.USER) {
            return new RequestResult(USER_FILM_PAGE , NavigationType.FORWARD);
        }

        return new RequestResult(ADMIN_FILM_PAGE, NavigationType.FORWARD);
    }
}
