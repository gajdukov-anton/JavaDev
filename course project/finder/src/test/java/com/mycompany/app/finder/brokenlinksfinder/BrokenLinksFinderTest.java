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
        String str = "BrokenLink --files page1.html www.google.com page2.html www.yandex.ru --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        List<Pair<String, String>> files = new ArrayList<>();
        files.add(new Pair<>("page1.html", "www.google.com"));
        files.add(new Pair<>("page2.html", "www.yandex.ru"));
        Assert.assertEquals(files, brokenLinksFinder.getFilesSource());

        String str2 = "BrokenLink --files  --out report.csv";
        String[] data2 = str2.split("( )+");
        brokenLinksFinder.setCommandData(data2);
        Assert.assertEquals(0, brokenLinksFinder.getFilesSource().size());
    }

    @Test
    public void getLinkSource() {
        String str = "BrokenLinks --links http://links.testingcourse.ru/page1.html --out report.csv";
        String[] data = str.split("( )+");
        BrokenLinksFinder brokenLinksFinder = new BrokenLinksFinder(data);
        List<String> links = new ArrayList<>();
        links.add("http://links.testingcourse.ru/page1.html");
        Assert.assertEquals(links, brokenLinksFinder.getLinksSource());

        String str2 = "BrokenLink --links    --out report.csv";
        String[] data2 = str2.split("( )+");
        brokenLinksFinder.setCommandData(data2);
        Assert.assertEquals(0, brokenLinksFinder.getLinksSource().size());
    }

    @Test
    public void run() {
    }

    @Test
    public void getLinksSource() {
    }

    @Test
    public void getFilesSource() {
    }
}