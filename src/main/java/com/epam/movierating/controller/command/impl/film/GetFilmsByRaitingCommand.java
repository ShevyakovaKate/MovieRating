package com.epam.movierating.controller.command.impl.film;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.Role;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.FilmService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

import java.util.List;

public class GetFilmsByRaitingCommand implements Command {
    private static final String MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.MOVIES_PAGE);
    private static final String USER_MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.USER_MOVIES_PAGE);
    private static final String ADMIN_MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.ADMIN_MOVIE_LIST_PAGE);
    private static final String HOMEPAGE_PATH = ConfigurationManager.getProperty(Paths.HOMEPAGE);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int MOVIES_PER_PAGE_DEFAULT_VALUE = 6;

    private FilmService filmService;

    public GetFilmsByRaitingCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        int pageNumber = DEFAULT_PAGE_NUMBER;

        String currentPage = requestContent.getRequestParameterByName(SessionAtr.CURRENT_PAGE);
        if(currentPage != null) {
            pageNumber = Integer.parseInt(currentPage);
        }

        int from = (pageNumber-1)*MOVIES_PER_PAGE_DEFAULT_VALUE;

        int numberOfFilms = filmService.countAllFilms();

        List<Film> filmList = filmService.getFilmListByRaiting(from, MOVIES_PER_PAGE_DEFAULT_VALUE);

        /**
         * ТУТ ПРОВЕРЯТЬ ВОШЕЛ ПОЛЬЗОВАТЕЛЬ ИЛИ НЕТ!!!*/

        if(filmList == null) {
            requestContent.setSessionAttributes(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.FILM_NOT_FOUND);
            return new RequestResult(HOMEPAGE_PATH, NavigationType.FORWARD);
        }

        int numberOfPages = (int) Math.ceil(numberOfFilms * 1.0 / MOVIES_PER_PAGE_DEFAULT_VALUE);

        String commandParameters = "";
        requestContent.setRequestAttributesForPagination(numberOfPages, pageNumber, commandParameters);
        requestContent.setSessionAttributes(SessionAtr.FILM_LIST, filmList);

        User user = (User)requestContent.getSessionAttributeByName(SessionAtr.USER);

        if(user == null) {
            return new RequestResult(MOVIE_LIST_PATH, NavigationType.FORWARD);
        }

        Role userRole = user.getRole();
        if(userRole == Role.USER) {
            return new RequestResult(USER_MOVIE_LIST_PATH , NavigationType.FORWARD);
        }

        return new RequestResult(ADMIN_MOVIE_LIST_PATH, NavigationType.FORWARD);

    }
}
