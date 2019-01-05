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
        List<Link> resultLink = getResultLinks("testFiles/1/Page1.html", "www.google.com");
        Assert.assertEquals(0, links.size());
        files.add(new Pair<>("testFiles/1/Page1.html", "www.google.com"));
        links = linkReader.readLinksFromFiles(files);
        Assert.assertEquals(6, links.size());
        Assert.assertEquals(resultLink, links);
    }

    @Test
    public void readLinksFromSite() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        List<Link> links = linkReader.readLinksFromSites(sites);
        List<Link> resultLinks = getResultLinks("http://links.testingcourse.ru/", "http://links.testingcourse.ru/");
        Assert.assertEquals(6, links.size());
        Assert.assertEquals(resultLinks, links);
        sites.add("http://links.testingcourse.ru/page1.html");
        sites.add("dssdvsl");
        sites.add("http://links.testingcourse.ru/page111.html");
        links = linkReader.readLinksFromSites(sites);
        Assert.assertEquals(11, links.size());
    }

    private List<Link> getResultLinks(String source, String baseUrl) {
        List<Link> resultLink = new ArrayList<>();
        resultLink.add(new Link("http://links.testingcourse.ru/page1.html", source, baseUrl));
        resultLink.add(new Link("javascript:alert('Hello');", source, baseUrl));
        resultLink.add(new Link("http://links.testingcourse.ru/page2.html", source, baseUrl));
        resultLink.add(new Link("http://www.yandex.ru", source, baseUrl));
        resultLink.add(new Link("http://links.testingcourse.ru/page1.html", source, baseUrl));
        resultLink.add(new Link("http://links.testingcourse.ru/index.html", source, baseUrl));
        return resultLink;
    }
}