package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.reader.LinkReader;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.Assert;
import org.junit.Test;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ScannerHandlerTest {

    @Test
    public void scanFilesTest() {
        List<Pair<String, String>> files = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        files.add(new Pair<>("testFiles/1/Page1.html", "http://links.testingcourse.ru/"));
        files.add(new Pair<>("testFiles/Pagesdvmsv", "dkzlv"));
        List<Link> links = linkReader.readLinksFromFiles(files);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("resultFiles/1/report.csv");
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
//        assertTrue(compareFile("resultFiles/1/result.csv", "resultFiles/1/report.csv"));
    }

    @Test
    public void scanSitesTest() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        sites.add("http://links.testingcourse.ru/page1.html");
        List<Link> links = linkReader.readLinksFromSites(sites);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("resultFiles/2/report.csv");
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
       // assertTrue(compareFile("resultFiles/2/report.csv", "resultFiles/2/result.csv"));
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