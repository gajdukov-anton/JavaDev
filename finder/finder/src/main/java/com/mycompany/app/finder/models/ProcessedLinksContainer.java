package com.mycompany.app.finder.models;

import java.util.ArrayList;
import java.util.List;

public class ProcessedLinksContainer {
    private List<ProcessedLink> normalLinks = new ArrayList<>();
    private List<ProcessedLink> brokenLinks = new ArrayList<>();

    public ProcessedLinksContainer() {

    }

    public ProcessedLinksContainer(ProcessedLinksContainer processedLinksContainer) {
        normalLinks.addAll(processedLinksContainer.getNormalLinks());
        brokenLinks.addAll(processedLinksContainer.getBrokenLinks());
    }

    public ProcessedLinksContainer(List<ProcessedLink> brokenLinks, List<ProcessedLink> normalLinks) {
        this.brokenLinks = brokenLinks;
        this.normalLinks = normalLinks;
    }

    public List<ProcessedLink> getBrokenLinks() {
        return brokenLinks;
    }

    public List<ProcessedLink> getNormalLinks() {
        return normalLinks;
    }
}
