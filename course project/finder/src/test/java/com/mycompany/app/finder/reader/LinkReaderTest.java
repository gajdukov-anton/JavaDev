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
        Assert.assertEquals(4, links.size());

        files.add(new Pair<>("testFiles/Page1.html", "www.google.com"));
        links = linkReader.readLinksFromFiles(files);
        Assert.assertEquals(9, links.size());
    }

    @Test
    public void readLinksFromSite() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        List<Link> links = linkReader.readLinksFromSites(sites);
        Assert.assertEquals(6, links.size());
    }
}