package com.mycompany.app.finder;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.reader.LinkReader;
import com.mycompany.app.finder.scannerhandler.ScannerHandler;
import com.mycompany.app.finder.writer.LinkWriter;
import javafx.util.Pair;

import java.util.List;

public class Finder implements IFinder {
    private List<Pair<String, String>> files;
    private List<Link> links;
    private ProcessedLinksContainer processedLinks;
    private LinkReader linkReader;
    private LinkWriter linkWriter;
    private ScannerHandler scannerHandler;

    public Finder(List<Pair<String, String>> files){
        this.files = files;
        linkReader = new LinkReader();
        linkWriter = new LinkWriter();
        scannerHandler = new ScannerHandler();
    }

    @Override
    public ProcessedLinksContainer findBrokenLinks() {
        links = linkReader.readLinksFromFile(files);
       // processedLinks = scannerHandler.scan(links);
        return processedLinks;
    }

//    public Finder(String fileName) {
//        this.fileName = fileName;
//        linkReader = new LinkReader();
//        scannerHandler = new ScannerHandler();
//        linkWriter = new LinkWriter();
//    }
//
//    public Finder() {
//        linkReader = new LinkReader();
//        scannerHandler = new ScannerHandler();
//        linkWriter = new LinkWriter();
//    }
//
//
//    public void setFileName(String fileName) {
//        this.fileName = fileName;
//    }
//
//    @Override
//    public ProcessedLinksContainer findBrokenLinks() {
//        links = linkReader.readLinksFromFile(fileName);
//        processedLinks = scannerHandler.scan(links);
//        return processedLinks;
//    }

    @Override
    public void createResultFile(String resultFileName) {
        linkWriter.setFileName(resultFileName);
        linkWriter.createReportFile(processedLinks);
    }
}
