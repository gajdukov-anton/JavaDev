package com.mycompany.app.finder.scanner;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;

public interface IScanner {
    Pair<Integer, String> scanLink(Link link);

    String getAbsUrl(Link link);
}
