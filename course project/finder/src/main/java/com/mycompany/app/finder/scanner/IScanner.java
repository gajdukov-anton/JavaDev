package com.mycompany.app.finder.scanner;

import javafx.util.Pair;

public interface IScanner {
    public boolean isBrokenLink(Pair<String, String> link);
}
