package com.mycompany.app.finder.scannerhandler;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;
import com.mycompany.app.finder.scanner.Scanner;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ScannerHandler implements IScannerHandler {
    private List<Future<Link>> tasks = new ArrayList<>();
    private List<Pair<String, String>> links = new ArrayList<>();
    private ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer();
    private ExecutorService service;

    public ScannerHandler(List<Pair<String, String>> links) {
        this.links = links;
        service = Executors.newFixedThreadPool(getMaxThreadAmount());
        processedLinksContainer = checkLinks(links);
    }

    public ScannerHandler() {
        service = Executors.newFixedThreadPool(getMaxThreadAmount());
    }

    public ProcessedLinksContainer scan(List<Pair<String, String>> links) {
        processedLinksContainer = checkLinks(links);
        return new ProcessedLinksContainer(processedLinksContainer);
    }

    public ProcessedLinksContainer checkLinks(List<Pair<String, String>> links) {
        ProcessedLinksContainer processedLinksContainer = new ProcessedLinksContainer();
        for (int i = 0; i < links.size(); i++) {
            tasks.add(service.submit(new Scanner(links.get(i))));
        }
        int i = 0;
        while (!tasks.isEmpty()) {
            if (tasks.get(i).isDone()) {
                Link link = null;
                try {
                    link = tasks.get(i).get();
                } catch (Exception exception) {
                    exception.printStackTrace();
                }

                if (link != null) {
                    if (link.isNormal()) {
                        processedLinksContainer.getNormalLinks().add(link);
                    } else {
                        processedLinksContainer.getBrokenLinks().add(link);
                    }
                }
                tasks.remove(i);
            }
            i++;
            if (i >= tasks.size()) {
                i = 0;
            }
        }
        return processedLinksContainer;
    }

   public ProcessedLinksContainer getProcessedLinksContainer() {
        return processedLinksContainer;
   }


    private int getMaxThreadAmount() {
        return 3;
    }

}
