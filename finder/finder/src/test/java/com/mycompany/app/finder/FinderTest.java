package com.mycompany.app.finder;

import com.mycompany.app.BaseTestFunctions;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FinderTest extends BaseTestFunctions {
    @Test
    public void scanSiteTest() {
        Finder finder = new Finder();
        List<String> sites = new ArrayList<>();
        sites.add("http://links.testingcourse.ru/");
        sites.add("http://linkjgjhs.testingcourse.ru/");
        sites.add("http://links.testingcourse.ru/page1.html");
        finder.setSites(sites);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("resultFiles/finderTest/1/report.csv");
        assertTrue(compareFile("resultFiles/finderTest/1/report.csv", "resultFiles/finderTest/1/result.csv"));
    }

    @Test
    public void scanFileTest() {
        Finder finder = new Finder();
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Page1.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/2/Page2.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/3/Page3.html", "http://links.testingcourse.ru"));
        finder.setFiles(files);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("resultFiles/finderTest/2/report.csv");
        assertTrue(compareFile("resultFiles/finderTest/2/report.csv", "resultFiles/finderTest/2/result.csv"));
    }

    @Test
    public void scanBrokenFileTest() {
        Finder finder = new Finder();
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Phsdhdhage1.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/2/Pshshshage2.html", "http://links.testingcourse.ru"));
        finder.setFiles(files);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("resultFiles/finderTest/3/report.csv");
        assertEquals(0, processedLinksContainer.getBrokenLinks().size());
        assertEquals(0, finder.getLinks().size());
        assertTrue(compareFile("resultFiles/finderTest/3/report.csv", "resultFiles/finderTest/3/result.csv"));
    }
}