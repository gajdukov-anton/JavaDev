package com.mycompany.app.finder.brokenlinksfinder;

import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BrokenLinksFinderTest {

    @Test
    public void getFileSourceTest() {
        String str = "BrokenLinks --files testFiles/1/Page1.html www.google.com testFiles/2/Page2.html www.yandex.ru --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        brokenLinksFinder.run();
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("testFiles/1/Page1.html", "www.google.com"));
        files.add(new Pair<>("testFiles/2/Page2.html", "www.yandex.ru"));
        assertEquals(files, brokenLinksFinder.getFilesSource());
        assertEquals("report.csv", brokenLinksFinder.getOutFileName());
        assertEquals("--files", brokenLinksFinder.getBrokenLinksSourceType());

    }

    @Test
    public void incorrectInputFilesTest() {
        String str = "BrokenLinks --files bjfask skjds --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        brokenLinksFinder.run();
        assertEquals(1, brokenLinksFinder.getFilesSource().size());
        assertEquals("report.csv", brokenLinksFinder.getOutFileName());
        assertEquals("--files", brokenLinksFinder.getBrokenLinksSourceType());
    }

    @Test
    public void incorrectInputAttributesTest() {
        String str = "BrokenLinks --files bjfask skjds";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        brokenLinksFinder.run();
        assertNull(brokenLinksFinder.getFiles());
        assertNull(brokenLinksFinder.getOutFileName());
        assertNull(brokenLinksFinder.getBrokenLinksSourceType());

        str = "BrokenLinks ssnglkngs --out dlf";
        data = str.split("( )+");
        brokenLinksFinder.setCommandData(data);
        brokenLinksFinder.run();
        assertNull(brokenLinksFinder.getFiles());
        assertNull(brokenLinksFinder.getOutFileName());
        assertNull( brokenLinksFinder.getBrokenLinksSourceType());
    }

    @Test
    public void incorrectInputUserDataTest() {
        String str = "BrokenLinks --files --out rexx";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);

        brokenLinksFinder.run();
        assertEquals(0, brokenLinksFinder.getFiles().size());
        assertNull(brokenLinksFinder.getOutFileName());
        assertEquals("--files", brokenLinksFinder.getBrokenLinksSourceType());


        str = "BrokenLinks --files bjfask skjds --out ";
        data = str.split("( )+");
        brokenLinksFinder.setCommandData(data);
        brokenLinksFinder.run();
        assertEquals(1, brokenLinksFinder.getFiles().size());
        assertNull(brokenLinksFinder.getOutFileName());
        assertEquals("--files", brokenLinksFinder.getBrokenLinksSourceType());
    }

    @Test
    public void getLinkSource() {
        String str = "BrokenLinks --links http://links.testingcourse.ru/page1.html --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        List<String> links = new ArrayList<>();
        links.add("http://links.testingcourse.ru/page1.html");
        brokenLinksFinder.run();
        Assert.assertEquals(links, brokenLinksFinder.getLinksSource());

        str = "BrokenLink --links    --out report.csv";
        String[] data2 = str.split("( )+");
        brokenLinksFinder.setCommandData(data2);
        brokenLinksFinder.run();
        Assert.assertEquals(0, brokenLinksFinder.getLinksSource().size());

        str = "BrokenLink --links http://links.testingcourse.ru/page1.html";
        data = str.split("( )+");


    }

}