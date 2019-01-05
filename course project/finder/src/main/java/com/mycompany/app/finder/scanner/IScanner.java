package com.mycompany.app.finder.scanner;

import com.mycompany.app.finder.models.Link;
import javafx.util.Pair;

public interface IScanner {
    public Pair<Integer, String> scanLink(Link link);

    public String getAbsUrl(Link link);
}
