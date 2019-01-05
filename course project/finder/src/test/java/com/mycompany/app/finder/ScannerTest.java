package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.scanner.Scanner;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ScannerTest {

    @Test
    public void getAbsUrlTest() {
        Link link = new Link("http://links.testingcourse.ru/page1.html", "http://links.testingcourse.ru");
        Scanner scanner = new Scanner(link);
        assertEquals("http://links.testingcourse.ru/page1.html", scanner.getAbsUrl(link));
        link = new Link("/page1.html", "http://links.testingcourse.ru");
        assertEquals("http://links.testingcourse.ru/page1.html", scanner.getAbsUrl(link));
        link = new Link("//passsgsbsfrrge1.html", "htttttp://links.testingcourse.ru");
        assertNull(scanner.getAbsUrl(link));
    }

    @Test
    public void scanLinkTest() {
        Link link = new Link("http://links.testingcourse.ru/page1.html", "http://links.testingcourse.ru");
        Pair<Integer, String> statusOk = new Pair<>(200, "OK");
        Pair<Integer, String> statusNotFound = new Pair<>(404, "HTTP error fetching URL");
        Scanner scanner = new Scanner(link);
        assertEquals(statusOk, scanner.scanLink(link));
        link = new Link("http://links.testingcourse.ru/page21.html", "http://links.testingcourse.ru");
//        assertEquals(statusNotFound, scanner.scanLink(link));
    }

    @Test
    public void callTest() {
        Link link = new Link("http://links.testingcourse.ru/page1.html", "http://links.testingcourse.ru");
        Scanner scanner = new Scanner(link);
        ProcessedLink processedLink = new ProcessedLink("http://links.testingcourse.ru/page1.html", "http://links.testingcourse.ru", "OK", 200, true );
        assertEquals(processedLink.getUrl(), scanner.call().getUrl());
        assertEquals(processedLink.getSource(), scanner.call().getSource());
        assertEquals(processedLink.getStatusCode(), scanner.call().getStatusCode());
        assertEquals(processedLink.getStatus(), scanner.call().getStatus());
        assertEquals(processedLink.isNormal(), scanner.call().isNormal());
        scanner = new Scanner(null);
        assertNull(scanner.call());
    }

}