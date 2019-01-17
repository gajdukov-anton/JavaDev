package com.mycompany.app.finder.scanner;

import com.mycompany.app.BaseTestFunctions;
import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.reader.LinkReader;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class ScannerHandlerTest extends BaseTestFunctions {

    @Test
    public void scanFilesTest() {
        List<Pair<String, String>> files = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        files.add(new Pair<>("testFiles/1/Page1.html", "http://links.testingcourse.ru/"));
        files.add(new Pair<>("testFiles/Pagesdvmsv", "dkzlv"));
        List<Link> links = linkReader.readLinksFromFiles(files);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("resultFiles/ScannerHandlerTest/1/report.csv");
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
        assertTrue(compareFile("resultFiles/ScannerHandlerTest/1/result.csv", "resultFiles/ScannerHandlerTest/1/report.csv"));
    }

    @Test
    public void scanSitesTest() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        sites.add("http://links.testingcourse.ru/page1.html");
        List<Link> links = linkReader.readLinksFromSites(sites);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("resultFiles/ScannerHandlerTest/2/report.csv");
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
        assertTrue(compareFile("resultFiles/ScannerHandlerTest/2/report.csv", "resultFiles/ScannerHandlerTest/2/result.csv"));
    }


}