package com.mycompany.app.finder.models;

public class Link {
    private String url;
    private String source;
    private String baseUrl;

    public Link(String url, String source) {
        this.source = source;
        this.url = url;
    }

    public Link(String url, String source, String baseUrl) {
        this.url = url;
        this.source = url;
        this.baseUrl = baseUrl;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getBaseUrl() {
        return baseUrl;
    }
}
