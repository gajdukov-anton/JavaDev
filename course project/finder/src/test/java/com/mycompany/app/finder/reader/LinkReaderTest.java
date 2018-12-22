package com.mycompany.app.finder.reader;

import javafx.util.Pair;
import org.junit.Test;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;


public class LinkReaderTest {

    @Test
    public void readLinksFromFile() {
        String[] files = new String[2];
        files[0] = "testHtml.html";
        files[1] = "testHtml.html";
        List<Pair<String, String>> links = new ArrayList<>();
        links.add(new Pair<>("#p1", "testHtml.html"));
        links.add(new Pair<>("#p2", "testHtml.html"));
        links.add(new Pair<>("#p3", "testHtml.html"));
        links.add(new Pair<>("#p4", "testHtml.html"));
        links.add(new Pair<>("#p1", "testHtml.html"));
        links.add(new Pair<>("#p2", "testHtml.html"));
        links.add(new Pair<>("#p3", "testHtml.html"));
        links.add(new Pair<>("#p4", "testHtml.html"));

        LinkReader linkReader = new LinkReader();
        linkReader.readLinksFromFile(files);
        Assert.assertEquals(8, linkReader.getLinks().size());
        Assert.assertEquals(links, linkReader.getLinks());
    }


}