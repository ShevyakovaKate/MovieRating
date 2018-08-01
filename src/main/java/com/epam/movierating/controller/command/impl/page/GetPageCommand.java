package com.epam.movierating.controller.command.impl.page;

import com.epam.movierating.controller.command.Command;
import com.epam.movierating.util.constant.RequestParam;
import com.epam.movierating.util.constant.SessionAtr;
import com.epam.movierating.controller.content.NavigationType;
import com.epam.movierating.controller.content.RequestContent;
import com.epam.movierating.controller.content.RequestResult;
import com.epam.movierating.entity.Genre;
import com.epam.movierating.service.GenreService;
import com.epam.movierating.service.ServiceException;
import com.epam.movierating.service.impl.GenreServiceImpl;
import com.epam.movierating.util.resourse.ConfigurationManager;
import com.epam.movierating.util.resourse.Paths;
import javafx.scene.shape.Path;

import java.util.List;

public class GetPageCommand implements Command {
    @Override
    public RequestResult execute(RequestContent requestContent) throws ServiceException {
        String page = "";
        String jspPageParameter = requestContent.getRequestParameterByName(RequestParam.JSP_PAGE);

        PageType pageType = PageType.fromString(jspPageParameter);
        if(pageType == null) {
            pageType = PageType.NOT_FOUND_PAGE_JSP;
        }
        switch (pageType){
            case HOMEPAGE_JSP:
                page = Paths.HOMEPAGE;
                requestContent.setSessionAttributes(SessionAtr.GENRE_LIST, initGenreList());
                break;
            case USER_PROFILE_JSP:
                page = Paths.USER_PROFILE_PAGE;
                break;
            case USER_HOMEPAGE_JSP:
                page = Paths.USER_HOME_PAGE;
                break;
            case ADMIN_MOVIE_LIST_JSP:
                page = Paths.ADMIN_MOVIE_LIST_PAGE;
                break;
            case NOT_FOUND_PAGE_JSP:
                page = Paths.ERROR_PAGE;
                break;
            case FILM_JSP:
                page = Paths.FILM_PAGE;
                break;
            case MOVIE_LIST_PAGE:
                page = Paths.MOVIES_PAGE;
                break;
            case CHANGE_PASSWORD_JSP:
                page = Paths.USER_CHANGE_PASS_PAGE;
                break;
            case CHANGE_INFO_JSP:
                page = Paths.USER_CHANGE_INFO_PAGE;
                break;
            case ADMIN_PROFILE_JSP:
                page = Paths.ADMIN_PROFILE_PAGE;
                break;
            case ADD_FILM:
                page = Paths.ADMIN_ADD_FILM_PAGE;
                break;
        }

        String pagePath = ConfigurationManager.getProperty(page);
        return new RequestResult(pagePath, NavigationType.FORWARD);
    }

    private List<Genre> initGenreList() throws ServiceException {
        GenreService genreService= new GenreServiceImpl();
        return genreService.getGenresList();
    }

}
