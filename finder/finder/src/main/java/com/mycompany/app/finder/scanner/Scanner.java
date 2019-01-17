package com.mycompany.app.finder.scanner;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLink;
import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;

public class Scanner implements Callable<ProcessedLink>, IScanner {

    private Link link;

    public Scanner(Link link) {
        this.link = link;
    }

    public ProcessedLink call() {
        if (link != null) {
            Pair<Integer, String> statusInfo = scanLink(link);
            return new ProcessedLink(link.getUrl(), link.getSource(), statusInfo.getValue(), statusInfo.getKey(), isGoodLink(statusInfo.getKey()));
        }
        return null;
    }


    public Pair<Integer, String> scanLink(Link link) {
        String url = getAbsUrl(link);
        if (url != null) {
            Connection.Response response;
            try {
                response = Jsoup.connect(url).execute();
                return new Pair<>(response.statusCode(), response.statusMessage());
            } catch (HttpStatusException exception) {
                return new Pair<>(exception.getStatusCode(), exception.getMessage());
            } catch (IOException exception) {
                return new Pair<>(0, exception.getMessage());
            }
        } else {
            return new Pair<>(0, "Incorrect url");
        }
    }

    public String getAbsUrl(Link link) {
        try {
            URL url = new URL(link.getUrl());
            url.toURI();
            return link.getUrl();
        } catch (Exception exception) {
            try {
                URL url = new URL(link.getBaseUrl() + link.getUrl());
                url.toURI();
                return link.getBaseUrl() + link.getUrl();
            } catch (Exception e) {
                return null;
            }
        }
    }

    private boolean isGoodLink(int statusCode) {
        return statusCode >= 100 && statusCode < 300;
    }
}
