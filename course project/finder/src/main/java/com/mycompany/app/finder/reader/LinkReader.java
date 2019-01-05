package com.mycompany.app.finder.reader;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;


import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class LinkReader implements ILinkReader {

    private List<Link> links;

    public LinkReader() {
    }

    @Override
    public List<Link> readLinksFromFiles(List<Pair<String, String>> files) {
        links = new ArrayList<>();
        for (Pair<String, String> item : files) {
            String baseUrl = item.getValue();
            Path path = Paths.get(item.getKey());
            File file = new File(path.toString());
            try {
                Document doc = Jsoup.parse(file, "UTF-8");
                //System.out.println(item.getKey() + item.getValue());

                getLinksFromDocument(links, doc, item.getKey(), item.getValue());
            } catch (FileNotFoundException exception) {
                System.out.println("File: " + item.getKey() + " not found");
            } catch (IOException exception) {
                //  exception.printStackTrace();
            }
        }
        return links;
    }

    @Override
    public List<Link> readLinksFromSites(List<String> sites) {
        links = new ArrayList<>();
        for (String site : sites) {
            try {
                Connection.Response response = Jsoup.connect(site).execute();
                Document doc = response.parse();
                getLinksFromDocument(links, doc, site, site);
            } catch (HttpStatusException e) {
                System.out.println("Input link: " + site + " was damaged. Error code: " + e.getStatusCode());
            } catch (Exception e) {
                System.out.println("Input link: " + site + " was damaged." );
            }
        }
        return links;
    }

    private void getLinksFromDocument(List<Link> links, Document document, String resource, String baseUrl) {
        Elements linkElements = document.getElementsByTag("a");
        for (Element element : linkElements) {
            String url = element.absUrl("href");
            if (url.equals("")) {
                url = element.attr("href");
            }
            addToLinks(url, resource, baseUrl);
            url = element.absUrl("src");
            if (url.equals("")) {
                url = element.attr("src");
            }
            addToLinks(url, resource, baseUrl);
        }

    }

    private void addToLinks(String url, String fileName, String baseUrl) {
        if (!url.equals("")) {
            links.add(new Link(url, fileName, baseUrl));
        }
    }

}
