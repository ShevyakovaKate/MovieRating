package com.epam.movierating.controller.command.factory;


import com.epam.movierating.controller.command.Command;
import com.epam.movierating.controller.command.CommandMap;

public class FactoryCommand {
    public Command createCommand(String commandName) {
        return CommandMap.defineCommandType(commandName);
    }
}
