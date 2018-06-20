package com.epam.movierating.command.factory;


import com.epam.movierating.command.Command;
import com.epam.movierating.command.CommandMap;

public class FactoryCommand {
    public Command createCommand(String commandName) {
        return CommandMap.defineCommandType(commandName);
    }
}
