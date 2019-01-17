package com.mycompany.app.finder.brokenlinksanalyzer;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.*;

public class BrokenLinksAnalyzerTest {

    @Test
    public void getFileSourceTest() {
        String str = "BrokenLinks --files testFiles/1/Page1.html www.google.com testFiles/2/Page2.html www.yandex.ru --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(data);
        brokenLinksAnalyzer.run();
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Page1.html", "www.google.com"));
        files.add(new Pair<>("testFiles/2/Page2.html", "www.yandex.ru"));
        assertEquals(files, brokenLinksAnalyzer.getFilesSource());
        assertEquals("report.csv", brokenLinksAnalyzer.getOutFileName());
        assertEquals("--files", brokenLinksAnalyzer.getBrokenLinksSourceType());

    }

    @Test
    public void incorrectInputFilesTest() {
        String str = "BrokenLinks --files bjfask skjds --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(data);
        brokenLinksAnalyzer.run();
        assertEquals(1, brokenLinksAnalyzer.getFilesSource().size());
        assertEquals("report.csv", brokenLinksAnalyzer.getOutFileName());
        assertEquals("--files", brokenLinksAnalyzer.getBrokenLinksSourceType());
    }

    @Test
    public void incorrectInputAttributesTest() {
        String str = "BrokenLinks --files bjfask skjds";
        String[] data = str.split("( )+");
        BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(data);
        brokenLinksAnalyzer.run();
        assertNull(brokenLinksAnalyzer.getFiles());
        assertNull(brokenLinksAnalyzer.getOutFileName());
        assertNull(brokenLinksAnalyzer.getBrokenLinksSourceType());

        str = "BrokenLinks ssnglkngs --out dlf";
        data = str.split("( )+");
        brokenLinksAnalyzer.setCommandData(data);
        brokenLinksAnalyzer.run();
        assertNull(brokenLinksAnalyzer.getFiles());
        assertNull(brokenLinksAnalyzer.getOutFileName());
        assertNull( brokenLinksAnalyzer.getBrokenLinksSourceType());
    }

    @Test
    public void incorrectInputUserDataTest() {
        String str = "BrokenLinks --files --out rexx";
        String[] data = str.split("( )+");
        BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(data);

        brokenLinksAnalyzer.run();
        assertEquals(0, brokenLinksAnalyzer.getFiles().size());
        assertNull(brokenLinksAnalyzer.getOutFileName());
        assertEquals("--files", brokenLinksAnalyzer.getBrokenLinksSourceType());


        str = "BrokenLinks --files bjfask skjds --out ";
        data = str.split("( )+");
        brokenLinksAnalyzer.setCommandData(data);
        brokenLinksAnalyzer.run();
        assertEquals(1, brokenLinksAnalyzer.getFiles().size());
        assertNull(brokenLinksAnalyzer.getOutFileName());
        assertEquals("--files", brokenLinksAnalyzer.getBrokenLinksSourceType());
    }

    @Test
    public void getLinkSource() {
        String str = "BrokenLinks --links http://links.testingcourse.ru/page1.html --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksAnalyzer brokenLinksAnalyzer = new BrokenLinksAnalyzer(data);
        List<String> links = new ArrayList<>();
        links.add("http://links.testingcourse.ru/page1.html");
        brokenLinksAnalyzer.run();
        Assert.assertEquals(links, brokenLinksAnalyzer.getLinksSource());

        str = "BrokenLink --links    --out report.csv";
        String[] data2 = str.split("( )+");
        brokenLinksAnalyzer.setCommandData(data2);
        brokenLinksAnalyzer.run();
        Assert.assertEquals(0, brokenLinksAnalyzer.getLinksSource().size());

        str = "BrokenLink --links http://links.testingcourse.ru/page1.html";
        data = str.split("( )+");


    }

}