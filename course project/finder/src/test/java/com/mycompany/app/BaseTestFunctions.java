package com.mycompany.app;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BaseTestFunctions {

    public boolean compareFile(String filePathOne, String filePathTwo) {
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
            if (lineOne == null && lineTwo == null) {
                return true;
            }
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
