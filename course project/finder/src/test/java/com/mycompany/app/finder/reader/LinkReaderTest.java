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
        List<Link> links = linkReader.readLinksFromFile(files);
        Assert.assertEquals(4, links.size());

        files.add(new Pair<>("testFiles/Page1.html", "www.google.com"));
        links = linkReader.readLinksFromFile(files);
        Assert.assertEquals(9, links.size());
    }


}