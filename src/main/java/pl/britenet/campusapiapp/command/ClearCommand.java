package pl.britenet.campusapiapp.command;

import pl.britenet.campusapiapp.constant.CommandName;

public class ClearCommand extends Command{

    public ClearCommand() {
        super(CommandName.CLEAR);
    }

    @Override
    public void execute() {
        System.out.println(System.lineSeparator().repeat(50));
        System.out.println("Console cleared. Awaiting command: ");
    }
}
