package com.mycompany.app.finder.writer;

import com.mycompany.app.finder.models.ProcessedLinksContainer;

public interface ILinkWriter {
    public void createReportFile(ProcessedLinksContainer processedLinksContainer);

}
