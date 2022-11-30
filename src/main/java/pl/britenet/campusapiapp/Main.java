package pl.britenet.campusapiapp;

import pl.britenet.campusapiapp.command.*;
import pl.britenet.campusapiapp.database.DatabaseService;

import java.util.Optional;
import java.util.Scanner;

public class Main {

    public static boolean isRunning = true;

    public static void main(String[] args) {
        DatabaseService databaseService = new DatabaseService();
        CommandService commandService = new CommandService();
        commandService
                .registerCommand(new HelpCommand(commandService.getCommandList()))
                .registerCommand(new ExitCommand())
                .registerCommand(new GetCommand(databaseService))
                .registerCommand(new GetAllCommand(databaseService))
                .registerCommand(new GetPaginatedCommand(databaseService))
                .registerCommand(new InsertCommand(databaseService))
                .registerCommand(new UpdateCommand(databaseService))
                .registerCommand(new DeleteCommand(databaseService))
                .registerCommand(new ClearCommand());

        System.out.println("----- Program started. Awaiting input: -----");
        Scanner scanner = new Scanner(System.in);

        while (isRunning) {
            System.out.print("> ");
            String commandName = scanner.nextLine();
            Optional<Command> optionalCommand = commandService.findCommandByName(commandName);
            if (optionalCommand.isPresent()) {
                optionalCommand.get().execute();
            } else {
                System.out.println("Command does not exist.");
            }
        }
    }
}