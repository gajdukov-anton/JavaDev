package com.mycompany.app.finder.scannerhandler;

import com.mycompany.app.finder.models.ProcessedLinksContainer;
import javafx.util.Pair;

import java.util.List;

public interface IScannerHandler {
    public ProcessedLinksContainer checkLinks(List<Pair<String, String>> links);
}
