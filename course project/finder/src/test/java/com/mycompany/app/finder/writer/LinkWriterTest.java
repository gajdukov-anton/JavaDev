package com.mycompany.app.finder.writer;

import com.mycompany.app.finder.models.ProcessedLink;
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

    private List<ProcessedLink> createBrokenLinkList() {
        List<ProcessedLink> links = new ArrayList<>();
        ProcessedLink link = new ProcessedLink("http://links.testingcourse.ru/page12.html", "file.txt", "bad",404, true);
        for (int i = 0; i < 5; i++) {
            links.add(link);
        }
        return links;
    }

    private List<ProcessedLink> createNormalLinkList() {
        List<ProcessedLink> links = new ArrayList<>();
        ProcessedLink link = new ProcessedLink("http://links.testingcourse.ru/page2.html", "file.txt", "norm", 200, false);
        for (int i = 0; i < 5; i++) {
            links.add(link);
        }
        return links;
    }
}