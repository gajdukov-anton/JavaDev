package com.mycompany.app.finder.writer;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinkWriterTest {

    @Test
    public void createReportFile() {
        LinkWriter linkWriter = new LinkWriter("testReport.csv");
        ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer(createBrokenLinkList(), createNormalLinkList());
        linkWriter.createReportFile(processedLinksContainer);
    }

    private List<Link> createBrokenLinkList() {
        List<Link> links = new ArrayList<>();
        Link link = new Link("http://links.testingcourse.ru/page12.html", "file.txt", "bad",404, true);
        for (int i = 0; i < 5; i++) {
            links.add(link);
        }
        return links;
    }

    private List<Link> createNormalLinkList() {
        List<Link> links = new ArrayList<>();
        Link link = new Link("http://links.testingcourse.ru/page2.html", "file.txt", "norm", 200, false);
        for (int i = 0; i < 5; i++) {
            links.add(link);
        }
        return links;
    }
}