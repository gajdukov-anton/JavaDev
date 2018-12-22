package com.mycompany.app.finder.scanner;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;
import org.jsoup.Connection;
import org.jsoup.HttpStatusException;
import org.jsoup.Jsoup;

import java.io.IOException;
import java.net.URL;
import java.util.concurrent.Callable;


public class Scanner implements Callable<Link>, IScanner {

    private Pair<String, String> link;

    public Scanner(Pair<String, String> link) {
        this.link = link;
    }

    public Link call() {
        if (link != null ) {
            Pair<Integer, String> statusInfo = getStatusInfo();
            return new Link(link.getKey(), link.getValue(), statusInfo.getValue(), statusInfo.getKey(), isGoodLink(statusInfo.getKey()));
        }
        return null;
    }

    public boolean isBrokenLink(Pair<String, String> link) {
        try {
            URL url = new URL(link.getKey());
            url.toURI();
            return false;
        } catch (Exception exception) {
            try {
                URL url = new URL(link.getValue() + '/' + link.getKey());
                url.toURI();
                return false;
            } catch (Exception e) {
                return true;
            }
        }
    }

    public Pair<Integer, String> getStatusInfo() {
        Connection.Response response;
        try {
            response = Jsoup.connect(link.getKey()).execute();
            System.out.println(response.statusCode());
            return new Pair<>(response.statusCode(), response.statusMessage());
        }catch (HttpStatusException exception) {
            System.out.println(exception.getStatusCode());
            return new Pair<>(exception.getStatusCode(), exception.getMessage());
        } catch (IOException exception) {
            exception.printStackTrace();
            return null;
        }
    }

    private boolean isGoodLink(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
}