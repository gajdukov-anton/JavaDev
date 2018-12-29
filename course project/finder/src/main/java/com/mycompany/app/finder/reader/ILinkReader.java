package com.mycompany.app.finder.reader;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;

import java.util.List;

public interface ILinkReader {
    public List<Link> readLinksFromFiles(List<Pair<String, String>> files);

    public List<Link> readLinksFromSites(List<String> sites);
}
