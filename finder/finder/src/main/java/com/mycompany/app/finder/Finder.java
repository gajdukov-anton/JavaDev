package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.reader.LinkReader;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;

import java.util.List;

public class Finder implements IFinder {
    private List<Link> links;
    private ProcessedLinksContainer processedLinks = new ProcessedLinksContainer();
    private LinkReader linkReader = new LinkReader();
    private LinkWriter linkWriter = new LinkWriter();
    private ScannerHandler scannerHandler = new ScannerHandler();

    public Finder(){
    }

    public void setFiles(List<Pair<String, String>> files) {
        links = linkReader.readLinksFromFiles(files);
    }

    public void setSites(List<String> sites) {
        links = linkReader.readLinksFromSites(sites);
    }

    public ProcessedLinksContainer getProcessedLinks() {
        return processedLinks;
    }

    @Override
    public ProcessedLinksContainer findBrokenLinks() {
        if (!links.isEmpty()) {
            processedLinks = scannerHandler.scan(links);
        }
        return processedLinks;
    }

    public List<Link> getLinks() {
        return links;
    }

    @Override
    public void createResultFile(String resultFileName) {
        linkWriter.setFileName(resultFileName);
        linkWriter.createReportFile(processedLinks);
    }
}
