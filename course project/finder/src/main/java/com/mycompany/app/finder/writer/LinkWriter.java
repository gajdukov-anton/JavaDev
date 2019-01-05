package com.mycompany.app.finder.writer;

import au.com.bytecode.opencsv.CSVWriter;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.models.ProcessedLinksContainer;

import java.io.FileWriter;
import java.io.IOException;

public class LinkWriter implements ILinkWriter {

    private String fileName;

    public LinkWriter(String fileName) {
        this.fileName = fileName;
    }

    public LinkWriter() {

    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void createReportFile(ProcessedLinksContainer processedLinksContainer) {
        try (CSVWriter writer = new CSVWriter(new FileWriter(fileName))) {
            for (ProcessedLink link : processedLinksContainer.getBrokenLinks()) {
                String strLinkData = link.getSource()+ "," + link.getUrl() + "," + String.valueOf(link.getStatusCode()) + "," + link.getStatus();
                String [] arrayLinkData = strLinkData.split(",");
                writer.writeNext(arrayLinkData);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
        }

    }

}
