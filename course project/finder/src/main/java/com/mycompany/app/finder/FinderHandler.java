package com.mycompany.app.finder;

import com.mycompany.app.finder.brokenlinksfinder.BrokenLinksFinder;

public class FinderHandler {

    private static final String FIND_BROKEN_LINKS = "BrokenLinks";
    private String[] commandData;

    public FinderHandler() {

    }

    public void doCommand(String command) {
        commandData = command.split("( )+");
        for (String str : commandData) {
            System.out.println(str);
        }
        if (commandData.length > 0) {
            runCommand(commandData);
        } else {
            System.out.println("Please, set the command.");
        }

    }

    public void doCommand(String[] commandData) {
        for (String str : commandData) {
            System.out.println(str);
        }
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
                BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(commandData);
                brokenLinksFinder.run();
                break;
            default:
                System.out.println("Command is not recognized.");
        }
    }

}
