package com.mycompany.app.finder.models;

import java.util.ArrayList;
import java.util.List;

public class ProcessedLinksContainer {
    private List<Link> normalLinks = new ArrayList<>();
    private List<Link> brokenLinks = new ArrayList<>();

    public ProcessedLinksContainer() {

    }

    public ProcessedLinksContainer(ProcessedLinksContainer processedLinksContainer) {
        normalLinks.addAll(processedLinksContainer.getNormalLinks());
        brokenLinks.addAll(processedLinksContainer.getBrokenLinks());
    }

    public ProcessedLinksContainer(List<Link> brokenLinks, List<Link> normalLinks) {
        this.brokenLinks = brokenLinks;
        this.normalLinks = normalLinks;
    }

    public List<Link> getBrokenLinks() {
        return brokenLinks;
    }

    public List<Link> getNormalLinks() {
        return normalLinks;
    }
}
