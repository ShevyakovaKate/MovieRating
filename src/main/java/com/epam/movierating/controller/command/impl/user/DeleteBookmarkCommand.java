package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.entity.User;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.UserService;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;

public class DeleteBookmarkCommand implements Command{

    private static final String FILM_PAGE = ConfigurationManager.getProperty(Paths.FILM_COMMAND);

    private UserService userService;

    public DeleteBookmarkCommand(UserService userService) {
            this.userService = userService;
        }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {

        String filmIdParam = requestContent.getRequestParameterByName(RequestParam.FILM_ID);
        int filmId = Integer.valueOf(filmIdParam);

        String userIdParam = requestContent.getRequestParameterByName(RequestParam.USER_ID_PARAM);
        int userId = Integer.valueOf(userIdParam);

        userService.deleteBookmark(filmId, userId);

        return new RequestResult(FILM_PAGE + filmId, NavigationType.REDIRECT);
    }
}
