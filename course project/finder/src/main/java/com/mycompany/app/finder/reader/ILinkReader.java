package com.mycompany.app.finder.reader;

import javafx.util.Pair;

import java.util.List;

public interface ILinkReader {
    public List<Pair<String, String>> readLinksFromFile(String fileName);
}
