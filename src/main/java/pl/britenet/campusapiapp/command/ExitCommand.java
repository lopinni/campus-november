package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;
import pl.britenet.campusapiapp.Main;

public class ExitCommand extends Command {

    public ExitCommand() {
        super(Constants.COMMAND_NAME_EXIT);
    }

    @Override
    public void execute() {
        Main.isRunning = false;
        System.out.println("Exiting...");
    }
}
