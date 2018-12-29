package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.reader.LinkReader;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScannerHandlerTest {

    @Test
    public void scanFilesTest() {
        List<Pair<String, String>> files = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        files.add(new Pair<>("testFiles/Page1.html", "www.google.com"));
        files.add(new Pair<>("testFiles/Pagesdvmsv", "dkzlv"));
        files.add(new Pair<>("testFiles/Page2.html", "www.google.com"));
        List<Link> links = linkReader.readLinksFromFiles(files);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("report.csv");
        //Assert.assertEquals(1);
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
    }

    @Test
    public void scanSitesTest() {
        List<String> sites = new ArrayList<>();
        LinkReader linkReader = new LinkReader();
        sites.add("http://links.testingcourse.ru/");
        List<Link> links = linkReader.readLinksFromSites(sites);
        ScannerHandler scannerHandler = new ScannerHandler(links);
        LinkWriter linkWriter = new LinkWriter("report.csv");
        //Assert.assertEquals(1);
        linkWriter.createReportFile(scannerHandler.getProcessedLinksContainer());
    }

    private List<Pair<String, String>> createLinks() {
        List<Pair<String, String>> links = new ArrayList<>();
        links.add(new Pair<>("http://links.testingcourse.ru/page12.html", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page13.html", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page15.html", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page1.html", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page3.html", "testHtml.html"));
        links.add(new Pair<>("https://yandex.ru/", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page10.html", "testHtml.html"));
        links.add(new Pair<>("http://links.testingcourse.ru/page17.html", "testHtml.html"));
        return links;
    }

    private List<Pair<String, String>> createBrokenLinks() {
        List<Pair<String, String>> brokenLinks = new ArrayList<>();
        brokenLinks.add(new Pair<>("#p1", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p2", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p3", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p4", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p5", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p6", "testHtml.html"));
        brokenLinks.add(new Pair<>("#p7", "testHtml.html"));
        return brokenLinks;
    }

    private List<ProcessedLink> createNormalLinks() {
        List<ProcessedLink> normalLinks = new ArrayList<>();
        // normalLinks.add(new ProcessedLink("https://yandex.ru/", "testHtml.html", 200, true));
        return normalLinks;
    }

    private boolean isListContainsSameLinks(List<ProcessedLink> linksOne, List<ProcessedLink> linksTwo) {
        if (linksOne.size() != linksTwo.size()) {
            return false;
        }
        for (ProcessedLink link : linksOne) {
            if (!linksTwo.contains(link)) {
                return false;
            }
        }
        return true;
    }


}