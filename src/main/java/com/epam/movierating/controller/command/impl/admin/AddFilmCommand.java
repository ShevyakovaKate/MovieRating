package com.epam.movierating.controller.command.impl.admin;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.FilmService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.HashPasswordUtil;
import com.epam.movierating.util.ValidationUtil;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;

import com.epam.movierating.util.resourse.Paths;

public class AddFilmCommand implements Command {

    private static final String ADMIN_MOVIE_LIST_PAGE = ConfigurationManager.getProperty(Paths.ADMIN_MOVIE_LIST_PAGE);
    private static final String ADD_FILM_PAGE = ConfigurationManager.getProperty(Paths.ADMIN_ADD_FILM_PAGE);

    private FilmService filmService;

    public AddFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        String name = requestContent.getRequestParameterByName(RequestParam.NAME_PARAM);
        String yearParam = requestContent.getRequestParameterByName(RequestParam.YEAR_PARAM);
        String director = requestContent.getRequestParameterByName(RequestParam.DIRECTOR_PARAM);
        String description = requestContent.getRequestParameterByName(RequestParam.DESCRIPTION_PARAM);


        if (!ValidationUtil.isYearValid(yearParam)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.YEAR_ERROR);
            return new RequestResult(ADD_FILM_PAGE, NavigationType.REDIRECT);
        }

        int year = Integer.parseInt(yearParam);

        /*Поставить нормальную валидацию имени и названия фильма*/

        if (!ValidationUtil.isInfoValid(name)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.NAME_ERROR);
            return new RequestResult(ADD_FILM_PAGE, NavigationType.FORWARD);
        }

        if (!ValidationUtil.isInfoValid(director)) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.DIRECTOR_ERROR);
            return new RequestResult(ADD_FILM_PAGE, NavigationType.FORWARD);
        }

        Film film = new Film();
        film.setName(name);
        film.setYearOfIssue(year);
        film.setDirector(director);
        film.setDescription(description);

        filmService.addFilm(film);

        return new RequestResult(ADMIN_MOVIE_LIST_PAGE, NavigationType.FORWARD);
    }
}
