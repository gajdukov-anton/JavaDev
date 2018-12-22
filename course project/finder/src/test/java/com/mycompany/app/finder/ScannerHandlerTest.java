package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ScannerHandlerTest {

    @Test
    public void testCase() {
        ScannerHandler finder = new ScannerHandler();
        List<Pair<String, String>> links = createLinks();
        ProcessedLinksContainer processedLinksContainer = finder.scan(links);
        LinkWriter linkWriter = new LinkWriter("file.csv");
        linkWriter.createReportFile(processedLinksContainer);
    }

//    private ProcessedLinksContainer createProcessedLinkContainer() {
//
//    }

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

    private List<Link> createNormalLinks() {
        List<Link> normalLinks = new ArrayList<>();
        // normalLinks.add(new Link("https://yandex.ru/", "testHtml.html", 200, true));
        return normalLinks;
    }

    private boolean isListContainsSameLinks(List<Link> linksOne, List<Link> linksTwo) {
        if (linksOne.size() != linksTwo.size()) {
            return false;
        }
        for (Link link : linksOne) {
            if (!linksTwo.contains(link)) {
                return false;
            }
        }
        return true;
    }


}