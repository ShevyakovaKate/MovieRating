package com.epam.movierating.controller.command;

import com.epam.movierating.controller.command.impl.*;
import com.epam.movierating.controller.command.impl.admin.AddFilmCommand;
import com.epam.movierating.controller.command.impl.admin.DeleteFilmCommand;
import com.epam.movierating.controller.command.impl.film.*;
import com.epam.movierating.controller.command.impl.page.GetPageCommand;
import com.epam.movierating.controller.command.impl.user.*;
import com.epam.movierating.service.impl.FilmServiceImpl;
import com.epam.movierating.service.impl.GenreServiceImpl;
import com.epam.movierating.service.impl.ReviewServiceImpl;
import com.epam.movierating.service.impl.UserServiceImpl;

import java.util.*;

import static com.epam.movierating.controller.command.CommandType.*;

public class CommandMap {

    private EnumMap<CommandType, Command> map = new EnumMap<>(CommandType.class);
    private static CommandMap instance = new CommandMap();

    private CommandMap() {
        map.put(LOGIN, new LoginCommand(new UserServiceImpl()));
        map.put(SIGN_UP, new SignUpCommand(new UserServiceImpl()));
        map.put(LOCALE, new LocaleCommand());
        map.put(GET_PAGE, new GetPageCommand());
        map.put(GET_FILMS_BY_YEAR, new GetFilmsByYearCommand(new FilmServiceImpl()));
        map.put(GET_FILMS_BY_RAITING, new GetFilmsByRaitingCommand(new FilmServiceImpl()));
        map.put(GET_FILM, new GetFilmCommand(new FilmServiceImpl(), new GenreServiceImpl(), new ReviewServiceImpl()));
        map.put(SEARCH_FILM, new SearchCommand(new FilmServiceImpl()));
        map.put(LOGOUT, new LogoutCommand());
        map.put(CHANGE_PASSWORD, new ChangePasswordCommand(new UserServiceImpl()));
        map.put(CHANGE_PERSONAL_INFO, new ChangePersonalInfoCommand(new UserServiceImpl()));
        map.put(ADD_REVIEW, new AddReviewCommand(new ReviewServiceImpl(), new UserServiceImpl(), new FilmServiceImpl()));
        map.put(ADD_BOOKMARK, new AddBookmarkCommand(new UserServiceImpl()));
        map.put(GET_BOOKMARKS, new GetBookmarksCommand(new FilmServiceImpl()));
        map.put(DELETE_BOOKMARK, new DeleteBookmarkCommand(new UserServiceImpl()));
        map.put(ADD_FILM, new AddFilmCommand(new FilmServiceImpl()));
        map.put(DELETE_FILM, new DeleteFilmCommand(new FilmServiceImpl()));
    }

    private static CommandMap getInstance() {
        return instance;
    }

    public static Command defineCommandType(String commandParameter) {
        Command command = null;
        EnumMap<CommandType, Command> commandMap = getInstance().map;

        CommandType commandType = CommandType.valueOf(commandParameter);
        for (Map.Entry<CommandType,Command> currentCommand : commandMap.entrySet()) {
            CommandType currentCommandType = currentCommand.getKey();
            if(currentCommandType.equals(commandType))
            {
                command = currentCommand.getValue();
                break;
            }
        }
        return command;
    }
}
