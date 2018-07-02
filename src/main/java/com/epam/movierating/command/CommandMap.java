package com.epam.movierating.command;

import com.epam.movierating.command.impl.LocaleCommand;
import com.epam.movierating.command.impl.LoginCommand;
import com.epam.movierating.command.impl.SignUpCommand;
import com.epam.movierating.command.page.GetPageCommand;
import com.epam.movierating.service.impl.UserServiceImpl;

import java.util.*;

import static com.epam.movierating.command.CommandType.*;

public class CommandMap {

    private EnumMap<CommandType, Command> map = new EnumMap<>(CommandType.class);
    private static CommandMap instance = new CommandMap();

    private CommandMap() {
        map.put(LOGIN, new LoginCommand(new UserServiceImpl()));
        map.put(SIGN_UP, new SignUpCommand(new UserServiceImpl()));
        map.put(LOCALE, new LocaleCommand());
        map.put(GETPAGE, new GetPageCommand());
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
