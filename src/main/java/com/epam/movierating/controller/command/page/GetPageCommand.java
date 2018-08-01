package com.epam.movierating.controller.command.page;

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
                page = "jsp/homepage.jsp";
                requestContent.setSessionAttributes(SessionAtr.GENRE_LIST, initGenreList());
                break;
            case USERHOME_JSP:
                page = "jsp/user/user-home.jsp";
                break;
            case ADMINHOME_JSP:
                page = "jsp/admin/movie-list-page.jsp";
                break;
            case NOT_FOUND_PAGE_JSP:
                page = "jsp/error/error.jsp";
                break;
        }

        String pagePath = page;
        return new RequestResult(pagePath, NavigationType.REDIRECT);
    }

    private List<Genre> initGenreList() throws ServiceException {
        GenreService genreService= new GenreServiceImpl();
        return genreService.getGenresList();
    }

}
