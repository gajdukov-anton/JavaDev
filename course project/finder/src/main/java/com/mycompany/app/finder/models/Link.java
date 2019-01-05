package com.mycompany.app.finder.models;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class Link {
    private String url;
    private String source;
    private String baseUrl;

    public Link(String url, String source) {
        this.source = source;
        this.url = url;
        this.baseUrl = source;
    }

    public Link(String url, String source, String baseUrl) {
        this.url = url;
        this.source = source;
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

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Link link = (Link) obj;
        return url.equals(link.url) && source.equals(link.source) && baseUrl.equals(link.baseUrl);
    }
}
