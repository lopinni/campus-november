package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Main;
import pl.britenet.campusapiapp.constant.CommandName;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(CommandName.EXIT);
    }

    @Override
    public void execute() {
        Main.isRunning = false;
        System.out.println("Exiting...");
    }
}
