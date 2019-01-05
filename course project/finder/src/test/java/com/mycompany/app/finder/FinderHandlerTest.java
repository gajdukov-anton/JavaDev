package com.mycompany.app.finder;

import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.Assert.*;

public class FinderHandlerTest {

    @Test
    public void doCommand() {
        FinderHandler finderHandler = new FinderHandler();
        finderHandler.doCommand("BrokenLinks --files testFiles/1/Page1.html http://links.testingcourse.ru testFiles/2/Page2.html http://links.testingcourse.ru --out resultFiles/3/report.csv");
       // assertTrue(compareFile("resultFiles/3/report.csv", "resultFiles/3/result.csv"));
        finderHandler.doCommand("BrokenLinks --files t/testFiles/Page1.html http://links.testingcourse.ru t/testFiles/Page2.html http://links.testingcourse.ru --out report.csv");
    }

    private boolean compareFile(String filePathOne, String filePathTwo) {
        Path pathOne = Paths.get(filePathOne);
        Path pathTwo = Paths.get(filePathTwo);
        File fileOne = new File(pathOne.toString());
        File fileTwo = new File(pathTwo.toString());

        try {
            FileReader fileReaderOne = new FileReader(fileOne);
            BufferedReader readerOne = new BufferedReader(fileReaderOne);
            FileReader fileReaderTwo = new FileReader(fileTwo);
            BufferedReader readerTwo = new BufferedReader(fileReaderTwo);
            String lineOne = readerOne.readLine();
            String lineTwo = readerTwo.readLine();
            if (!lineOne.equals(lineTwo)) {
                return false;
            }
            while (lineOne != null && lineTwo != null) {
                lineOne = readerOne.readLine();
                lineTwo = readerTwo.readLine();
                if (lineOne != null && lineTwo != null) {
                    if (!lineOne.equals(lineTwo)) {
                        return false;
                    }
                }
            }
            if (lineOne != null || lineTwo != null) {
                return false;
            }

        } catch (FileNotFoundException exception) {
            // System.out.println("File: " + item.getKey() + " not found");
        } catch (IOException exception) {
            //  exception.printStackTrace();
        }
        return true;
    }
}