package com.mycompany.app.finder;

import com.mycompany.app.finder.brokenlinksanalyzer.BrokenLinksAnalyzer;

public class FinderHandler {

    private static final String FIND_BROKEN_LINKS = "BrokenLinks";
    private String[] commandData;

    public FinderHandler() {

    }

    public void doCommand(String command) {
        commandData = command.split("( )+");
        if (command.length() > 0) {
            runCommand(commandData);
        } else {
            System.out.println("Please, set the command.");
        }

    }

    public void doCommand(String[] commandData) {
        this.commandData = commandData;
        if (commandData.length > 0) {
            runCommand(commandData);
        } else {
            System.out.println("Please, set the command in the parameters, when starting the program.");
        }
    }

    public String[] getCommandData() {
        return commandData;
    }

    private void runCommand(String[] commandData) {
        switch (commandData[0]) {
            case FIND_BROKEN_LINKS:
                BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(commandData);
                brokenLinksAnalyzer.run();
                break;
            default:
                System.out.println("Command is not recognized.");
        }
    }

}
