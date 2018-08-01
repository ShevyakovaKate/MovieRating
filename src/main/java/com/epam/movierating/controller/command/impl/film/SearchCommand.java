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
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SearchCommand implements Command{
    private static final String MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.MOVIES_PAGE);
    private static final String USER_MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.USER_MOVIES_PAGE);
    private static final String ADMIN_MOVIE_LIST_PATH = ConfigurationManager.getProperty(Paths.ADMIN_MOVIE_LIST_PAGE);
    private static final String ANY_GENRE = "any";
    private static final int DEFAULT_PAGE_NUMBER = 1;
    private static final int MOVIES_PER_PAGE_DEFAULT_VALUE = 3;


    private FilmService filmService;

    public SearchCommand(FilmService filmService) {
        this.filmService = filmService;
    }

    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        Map<String, String> searchParam = new HashMap<>();

        String searchingText = requestContent.getRequestParameterByName(RequestParam.SEARCHING_TEXT);
        searchParam.put(RequestParam.SEARCHING_TEXT, searchingText);

        String genreId = requestContent.getRequestParameterByName(RequestParam.GENRE_ID);
        if (!genreId.equalsIgnoreCase(ANY_GENRE)) {
            searchParam.put(RequestParam.GENRE_ID, genreId);
        }

        int pageNumber = DEFAULT_PAGE_NUMBER;

        String currentPage = requestContent.getRequestParameterByName(SessionAtr.CURRENT_PAGE);
        if(currentPage != null) {
            pageNumber = Integer.parseInt(currentPage);
        }

        int from = (pageNumber-1)*MOVIES_PER_PAGE_DEFAULT_VALUE;

        int numberOfFilms = filmService.countSearchingFilms(searchParam);
        List<Film> filmList = filmService.searchFilms(searchParam, from, MOVIES_PER_PAGE_DEFAULT_VALUE);

        int numberOfPages = (int) Math.ceil(numberOfFilms * 1.0 / MOVIES_PER_PAGE_DEFAULT_VALUE);

        String commandParameters = "&genre_id="+genreId+"&searching_text="+searchingText;
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
