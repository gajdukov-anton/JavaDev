package com.mycompany.app.finder.writer;

import com.mycompany.app.BaseTestFunctions;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class LinkWriterTest extends BaseTestFunctions {

    @Test
    public void createReportFile() {
        LinkWriter linkWriter = new LinkWriter("resultFiles/linkWriterTest/testReport.csv");
        ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer(createBrokenLinkList(), createNormalLinkList());
        linkWriter.createReportFile(processedLinksContainer);
        Assert.assertTrue(compareFile("resultFiles/linkWriterTest/testReport.csv", "resultFiles/linkWriterTest/result.csv"));
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