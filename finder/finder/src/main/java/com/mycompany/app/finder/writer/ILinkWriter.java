package com.mycompany.app.finder.writer;

import com.mycompany.app.finder.models.ProcessedLinksContainer;

public interface ILinkWriter {
    void createReportFile(ProcessedLinksContainer processedLinksContainer);
}
