package com.mycompany.app.finder;

import com.mycompany.app.BaseTestFunctions;
import org.junit.Test;

import static org.junit.Assert.*;

public class FinderHandlerTest extends BaseTestFunctions {

    @Test
    public void doCommand() {
        FinderHandler finderHandler = new FinderHandler();
        finderHandler.doCommand("BrokenLinks --files testFiles/1/Page1.html http://links.testingcourse.ru testFiles/2/Page2.html http://links.testingcourse.ru --out resultFiles/FinderHandlerTest/report.csv");
        assertTrue(compareFile("resultFiles/FinderHandlerTest/report.csv", "resultFiles/FinderHandlerTest/result.csv"));
        String[] data = new String[] {"BrokenLinks", "--files", "sf", "ddg", "--out", "resultFiles/FinderHandlerTest/fdd"};
        finderHandler.doCommand(data);
        assertEquals(data.length, finderHandler.getCommandData().length);
        data = new String[] {"rsss", "--files", "sf", "ddg", "--out", "resultFiles/FinderHandlerTest/fdd"};
        finderHandler.doCommand(data);
        data = new String[] {};
        finderHandler.doCommand(data);
        assertEquals(data.length, finderHandler.getCommandData().length);
        finderHandler.doCommand("");
        assertEquals(1, finderHandler.getCommandData().length);
    }

}