package com.epam.movierating.controller.command.impl.user;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Film;
import com.epam.movierating.service.FilmService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Messages;
import com.epam.movierating.util.resourse.Paths;

import java.util.List;

public class GetBookmarksCommand implements Command {

    private static final String BOOKMARKS_PATH = ConfigurationManager.getProperty(Paths.BOOKMARK_PAGE);
    private static final String HOMEPAGE_PATH = ConfigurationManager.getProperty(Paths.USER_HOME_PAGE);
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int MOVIES_PER_PAGE_DEFAULT_VALUE = 3;

    private FilmService filmService;

    public GetBookmarksCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        int pageNumber = DEFAULT_PAGE_NUMBER;

        String userIdParam = requestContent.getRequestParameterByName(RequestParam.USER_ID_PARAM);
        int userId = Integer.valueOf(userIdParam);

        String currentPage = requestContent.getRequestParameterByName(SessionAtr.CURRENT_PAGE);
        if(currentPage != null) {
            pageNumber = Integer.parseInt(currentPage);
        }

        int from = (pageNumber-1)*MOVIES_PER_PAGE_DEFAULT_VALUE;

        int numberOfBookmarks = filmService.countBookmarks(userId);

        List<Film> bookmarkList = filmService.getBookmarks(userId, from, MOVIES_PER_PAGE_DEFAULT_VALUE);

        /**
         * ТУТ ПРОВЕРЯТЬ ВОШЕЛ ПОЛЬЗОВАТЕЛЬ ИЛИ НЕТ!!!*/

        if(bookmarkList == null) {
            requestContent.setRequestAttribute(SessionAtr.OPERATION_STATUS_NEGATIVE, Messages.BOOKMARKS_NOT_FOUND);
            return new RequestResult(HOMEPAGE_PATH, NavigationType.FORWARD);
        }

        int numberOfPages = (int) Math.ceil(numberOfBookmarks * 1.0 / MOVIES_PER_PAGE_DEFAULT_VALUE);

        String commandParameters = "&user_id=" + userId;
        requestContent.setRequestAttributesForPagination(numberOfPages, pageNumber, commandParameters);
        requestContent.setRequestAttribute(SessionAtr.BOOKMARK_LIST, bookmarkList);

        return new RequestResult(BOOKMARKS_PATH, NavigationType.FORWARD);
    }
}
