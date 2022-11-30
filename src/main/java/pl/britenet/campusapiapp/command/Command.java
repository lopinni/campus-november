package pl.britenet.campusapiapp.command;

import java.util.Scanner;

public abstract class Command {

    private String name;

    public Command(String name) {
        this.name = name;
    }

    public abstract void execute();

    public String getName() {
        return name;
    }

    public String messageWithScanner(String message, Scanner scanner) {
        System.out.println(message);
        return scanner.nextLine();
    }
}
