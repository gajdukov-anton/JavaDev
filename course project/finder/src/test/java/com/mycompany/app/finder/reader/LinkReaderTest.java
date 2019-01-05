package com.mycompany.app.finder.reader;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class LinkReaderTest {

    @Test
    public void readLinksFromFile() {
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/testHtml.html", "www.google.com"));
        LinkReader linkReader = new LinkReader();
        List<Link> links = linkReader.readLinksFromFiles(files);
        Assert.assertEquals(0, links.size());
        files.add(new Pair<>("testFiles/1/Page1.html", "www.google.com"));
        links = linkReader.readLinksFromFiles(files);
        Assert.assertEquals(6, links.size());
    }

    @Test
    public void readLinksFromSite() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        sites.add("http://links.testingcourse.ru/page1.html");
        sites.add("dssdvsl");
        sites.add("http://links.testingcourse.ru/page111.html");
        List<Link> links = linkReader.readLinksFromSites(sites);
        Assert.assertEquals(11, links.size());
    }
}