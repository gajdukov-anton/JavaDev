package com.mycompany.app.finder;

import com.mycompany.app.finder.models.ProcessedLinksContainer;

public interface IFinder {
    public ProcessedLinksContainer findBrokenLinks();
    public void createResultFile(String resultFileName);
}
