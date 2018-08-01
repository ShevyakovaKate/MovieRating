package com.epam.movierating.controller.command.impl.admin;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.service.FilmService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

public class DeleteFilmCommand implements Command {

    private static final String ADMIN_MOVIE_LIST_PAGE = ConfigurationManager.getProperty(Paths.ADMIN_MOVIE_LIST_PAGE);

    private FilmService filmService;

    public DeleteFilmCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        String filmIdParam = requestContent.getRequestParameterByName(RequestParam.FILM_ID);

        int filmId = Integer.parseInt(filmIdParam);

        filmService.deleteFilm(filmId);

        requestContent.setSessionAttributes(SessionAtr.OPERATION_STATUS_POSITIVE, Messages.FILM_DELETED_SUCCESS);

        return new RequestResult(ADMIN_MOVIE_LIST_PAGE, NavigationType.FORWARD);
    }
}
