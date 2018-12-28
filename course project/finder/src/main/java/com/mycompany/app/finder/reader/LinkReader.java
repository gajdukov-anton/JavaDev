package com.mycompany.app.finder.reader;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;
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
    public List<Link> readLinksFromFile(List<Pair<String, String>> files) {

        links = new ArrayList<>();
        for (Pair<String, String> item : files) {
            String baseUrl = item.getValue();
            Path path = Paths.get(item.getKey());
            File file = new File(path.toString());
            try {
                Document doc = Jsoup.parse(file, "UTF-8");
                Elements linkElements = doc.getElementsByTag("a");
                for (Element element : linkElements) {
                    String url = element.attr("href");
                    addToLinks(url, item.getKey(), baseUrl);
                    url = element.attr("src");
                    addToLinks(url, item.getKey(), baseUrl);
                }
            } catch (FileNotFoundException exception) {
                // TODO: 06.12.2018 сделать logger
                System.out.println("File: " + files + " not found");
            } catch (IOException exception) {
                //  exception.printStackTrace();
            }
        }
        return links;
    }

    private void addToLinks(String url, String fileName, String baseUrl) {
        if (!url.equals("")) {
            links.add(new Link(url, fileName, baseUrl));
        }
    }

}
