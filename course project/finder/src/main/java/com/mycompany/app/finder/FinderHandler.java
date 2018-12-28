package com.mycompany.app.finder;

import java.util.ArrayList;
import java.util.List;

public class FinderHandler {

    private Finder finder;
    private String command;
    private String[] commandData;
    List<String> inputResource;
    String outFile;

    public FinderHandler() {

    }

    public void setCommand(String command) {
        commandData = command.split(" ");
        if (commandData.length > 0) {
            analiseCommand(commandData);
        } else {
            System.out.println("Please, set command.");
        }
    }

    public void DoCommand() {
        if (command != null) {

        } else {
            System.out.println("Please, set command.");
        }
    }

    public String getCommand() {
        return command;
    }

    public List<String> getInputResource() {
        return inputResource;
    }

    public String[] getCommandData() {
        return commandData;
    }

    public String getOutFile() {
        return outFile;
    }

    private void analiseCommand(String[] commandData) {
        switch (commandData[0]) {
            case "brokenLinks":
                if (analiseAttributes()) {
                    this.command = commandData[0];
                }
                break;
            default:
                System.out.println("Command is not recognized.");
        }
    }

    private boolean analiseAttributes() {

        if (!(arrayContainStr(commandData, "--files") || !(arrayContainStr(commandData, "--links")))) {
            System.out.println("Attribute --files/--links is not recognized");
            return false;
        }
        if (!(arrayContainStr(commandData, "--out"))) {
            System.out.println("Attribute --out is not recognized");
            return false;
        }
        inputResource = getInputSource();
        outFile = getOutputFile();
        return true;
    }

    private List<String> getInputSource() {
        List<String> inputSource = new ArrayList<>();
        int counter = 2;
        while (!(commandData[counter].equals("--out"))) {
            inputSource.add(commandData[counter]);
        }
        return inputSource;
    }

    private String getOutputFile() {
        if (commandData[commandData.length - 1].equals("--out")) {
            return null;
        } else {
            return commandData[commandData.length - 1];
        }
    }

    private boolean arrayContainStr(String[] array, String str) {
        for (String item : array) {
            if (item.equals(str)) {
                return true;
            }
        }
        return false;
    }
}
