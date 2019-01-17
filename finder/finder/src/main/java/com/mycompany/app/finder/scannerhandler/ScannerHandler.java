package com.mycompany.app.finder.scannerhandler;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLink;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.scanner.Scanner;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScannerHandler implements com.mycompany.app.finder.scannerhandler.IScannerHandler {

    private static final String PATH_TO_PROPERTIES = "src/main/resources/config.properties";
    private List<Future<ProcessedLink>> tasks = new ArrayList<>();
    private List<Link> links = new ArrayList<>();
    private ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer();
    private ExecutorService service;

    public ScannerHandler(List<Link> links) {
        this.links = links;
        service = Executors.newFixedThreadPool(getMaxThreadAmount());
        processedLinksContainer = checkLinks(links);
    }

    public ScannerHandler() {
        service = Executors.newFixedThreadPool(getMaxThreadAmount());
    }

    public ProcessedLinksContainer scan(List<Link> links) {
        processedLinksContainer = checkLinks(links);
        return new ProcessedLinksContainer(processedLinksContainer);
    }

    public ProcessedLinksContainer checkLinks(List<Link> links) {
        ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer();
        for (int i = 0; i < links.size(); i++) {
            tasks.add(service.submit(new Scanner(links.get(i))));
        }
        int i = 0;
        while (!tasks.isEmpty()) {
            if (tasks.get(i).isDone()) {
                ProcessedLink processedLink = null;
                try {
                    processedLink = tasks.get(i).get();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                if (processedLink != null) {
                    if (processedLink.isNormal()) {
                        processedLinksContainer.getNormalLinks().add(processedLink);
                    } else {
                        processedLinksContainer.getBrokenLinks().add(processedLink);
                    }
                }
                tasks.remove(i);
            }
            i++;
            if (i >= tasks.size()) {
                i = 0;
            }
        }
        service.shutdown();
        return processedLinksContainer;
    }

   public ProcessedLinksContainer getProcessedLinksContainer() {
        return processedLinksContainer;
   }


    private int getMaxThreadAmount() {
        FileInputStream fileInputStream;
        Properties properties = new Properties();
        try {
            fileInputStream = new FileInputStream(PATH_TO_PROPERTIES);
            properties.load(fileInputStream);
            return Integer.valueOf(properties.getProperty("amountThread"));
        } catch (IOException e) {
            return 1;
        }
    }

}
