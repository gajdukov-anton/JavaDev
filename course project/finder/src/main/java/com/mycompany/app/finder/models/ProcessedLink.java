package com.mycompany.app.finder.models;

import com.sun.org.apache.xpath.internal.operations.Equals;

public class ProcessedLink {
    private String url;
    private String source;
    private String status;
    private Integer statusCode;
    private Boolean isNormal;

    public ProcessedLink(String url, String source, String status, Integer statusCode, Boolean isNormal) {
        this.url = url;
        this.source = source;
        this.status = status;
        this.statusCode = statusCode;
        this.isNormal = isNormal;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public String getSource() {
        return source;
    }

    public String getUrl() {
        return url;
    }

    public Boolean isNormal() {
        return isNormal;
    }

    public String getStatus() {
        return status;
    }
}
