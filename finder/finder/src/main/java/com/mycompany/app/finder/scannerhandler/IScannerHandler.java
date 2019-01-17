package com.mycompany.app.finder.scannerhandler;

import com.mycompany.app.finder.models.Link;
import com.mycompany.app.finder.models.ProcessedLinksContainer;

import java.util.List;

public interface IScannerHandler {
    ProcessedLinksContainer checkLinks(List<Link> links);
}
