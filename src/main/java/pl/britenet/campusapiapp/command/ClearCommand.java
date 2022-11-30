package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.Constants;

public class ClearCommand extends Command{

    public ClearCommand() {
        super(Constants.COMMAND_NAME_CLEAR);
    }

    @Override
    public void execute() {
        System.out.println(System.lineSeparator().repeat(50));
        System.out.println("Console cleared. Awaiting command: ");
    }
}
