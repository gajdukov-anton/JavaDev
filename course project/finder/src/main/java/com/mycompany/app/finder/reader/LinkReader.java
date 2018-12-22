package com.mycompany.app.finder.reader;

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

    private List<Pair<String, String>> links;

    public LinkReader() {
    }

    @Override
    public List<Pair<String, String>> readLinksFromFile(String fileName) {
        links = new ArrayList<>();
        Path path = Paths.get(fileName);
        File file = new File(path.toString());
        try {
            Document doc = Jsoup.parse(file, "UTF-8");
            Elements linkElements = doc.getElementsByTag("a");
            for (Element item : linkElements) {
                String link = item.attr("href");
                addToLinks(link, fileName);
                link = item.attr("src");
                addToLinks(link, fileName);
            }
        } catch (FileNotFoundException exception) {
            // TODO: 06.12.2018 сделать logger
            System.out.println("File: " + fileName + " not found");
        } catch (IOException exception) {
            //  exception.printStackTrace();
        } finally {
            return links;
        }
    }

    private void addToLinks(String link, String fileName) {
        if (!link.equals("")) {
            links.add(new Pair<>(link, fileName));
        }
    }

}
