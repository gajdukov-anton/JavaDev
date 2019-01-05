package com.mycompany.app.finder;

import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FinderTest {
    @Test
    public void scanSiteTest() {
        Finder finder = new Finder();
        List<String> sites = new ArrayList<>();
        sites.add("http://links.testingcourse.ru/");
        sites.add("http://linkjgjhs.testingcourse.ru/");
        sites.add("http://links.testingcourse.ru/");

        finder.setSites(sites);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("report1.csv");

    }

    @Test
    public void scanFileTest() {
        Finder finder = new Finder();
        List<Pair<String,String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Page1.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/2/Page2.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/Page3.html", "http://links.testingcourse.ru"));

        finder.setFiles(files);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("report2.csv");
    }

    @Test
    public void scanBrokenFileTest() {
        Finder finder = new Finder();
        List<Pair<String,String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Phsdhdhage1.html", "http://links.testingcourse.ru"));
        files.add(new Pair<>("testFiles/2/Pshshshage2.html", "http://links.testingcourse.ru"));

        finder.setFiles(files);
        ProcessedLinksContainer processedLinksContainer = finder.findBrokenLinks();
        finder.createResultFile("report3.csv");
    }

}