package com.mycompany.app.finder;

import com.mycompany.app.finder.models.ProcessedLinksContainer;

public interface IFinder {
    ProcessedLinksContainer findBrokenLinks();
    void createResultFile(String resultFileName);
}
