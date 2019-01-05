package com.mycompany.app.finder.brokenlinksfinder;

import com.mycompany.app.finder.Finder;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class BrokenLinksFinder implements IBrokenLinksFinder {
    private static final String BROKEN_LINKS_FILE_SOURCE = "--files";
    private static final String BROKEN_LINKS_LINK_SOURCE = "--links";
    private static final String OUT_SOURCE = "--out";

    private String[] commandData;
    private String brokenLinksSourceType;
    private List<String> links;
    private List<Pair<String, String>> files;
    private String outFileName;


    public BrokenLinksFinder(String[] commandData) {
        this.commandData = commandData;
    }

    public void setCommandData(String[] commandData) {
        brokenLinksSourceType = null;
        links = null;
        files = null;
        outFileName = null;
        this.commandData = commandData;
    }


    public void run() {
        if (analiseBrokenLinksAttributes() && getBrokenLinksSource() && setOutputFile()) {
            Finder finder = new Finder();
            switch (brokenLinksSourceType) {
                case BROKEN_LINKS_FILE_SOURCE:
                    finder.setFiles(files);
                    finder.findBrokenLinks();
                    finder.createResultFile(outFileName);
                    System.out.println("Amount broken links: " + finder.getProcessedLinks().getBrokenLinks().size());
                    break;
                case BROKEN_LINKS_LINK_SOURCE:
                    finder.setSites(links);
                    finder.findBrokenLinks();
                    finder.createResultFile(outFileName);
                    System.out.println("Amount broken links: " + finder.getProcessedLinks().getBrokenLinks().size());
                    break;
            }
        } else {
            System.out.println("Unable to search. Please, check input data");
        }
    }

    public String[] getCommandData() {
        return commandData;
    }

    public List<Pair<String, String>> getFiles() {
        return files;
    }

    public List<String> getLinks() {
        return links;
    }

    public String getOutFileName() {
        return outFileName;
    }


    private boolean getBrokenLinksSource() {
        switch (brokenLinksSourceType) {
            case BROKEN_LINKS_FILE_SOURCE:
                files = getFilesSource();
                if (files.isEmpty()) {
                    System.out.println("Incorrect pair of files and base url");
                    return false;
                } else {
                    return true;
                }
            case BROKEN_LINKS_LINK_SOURCE:
                links = getLinksSource();
                if (links.isEmpty()) {
                    System.out.println("Incorrect input links");
                    return false;
                } else {
                    return true;
                }
            default:
                return false;
        }
    }

    public List<String> getLinksSource() {
        List<String> linkSource = new ArrayList<>();
        int counter = 0;
        String str = "";
        while (!str.equals(BROKEN_LINKS_LINK_SOURCE)) {
            str = commandData[counter];
            counter++;
        }
        while (!commandData[counter].equals(OUT_SOURCE)) {
            linkSource.add(commandData[counter]);
            counter++;
        }
        return linkSource;
    }

    public List<Pair<String, String>> getFilesSource() {
        List<Pair<String, String>> fileSource = new ArrayList<>();
        int counter = 0;
        String file = "";
        while (!file.equals(BROKEN_LINKS_FILE_SOURCE)) {
            file = commandData[counter];
            counter++;
        }
        while (!commandData[counter].equals(OUT_SOURCE) && !commandData[counter + 1].equals(OUT_SOURCE)) {
            fileSource.add(new Pair<>(commandData[counter], commandData[counter + 1]));
            counter += 2;
        }
        return fileSource;
    }


    private boolean analiseBrokenLinksAttributes() {
        if (!(arrayContainStr(commandData, OUT_SOURCE))) {
            System.out.println("Attribute --out is not recognized");
            return false;
        }

        if (arrayContainStr(commandData, BROKEN_LINKS_FILE_SOURCE)) {
            brokenLinksSourceType = BROKEN_LINKS_FILE_SOURCE;
            return true;
        }
        if (arrayContainStr(commandData, BROKEN_LINKS_LINK_SOURCE)) {
            brokenLinksSourceType = BROKEN_LINKS_LINK_SOURCE;
            return true;
        }

        System.out.println("Attribute --files/--links is not recognized");
        return false;
    }


    private boolean setOutputFile() {
        if (commandData[commandData.length - 1].equals("--out")) {
            System.out.println("Out file is not found");
            return false;
        } else {
            outFileName = commandData[commandData.length - 1];
            return true;
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
