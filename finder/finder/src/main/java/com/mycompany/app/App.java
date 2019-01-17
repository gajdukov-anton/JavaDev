package com.mycompany.app;

import com.mycompany.app.finder.FinderHandler;

import java.util.Scanner;

/**
 * Hello world!
 */
public class App {

    private static final String END_OF_WORK = "Exit";

    public static void main(String[] args) {
        FinderHandler finderHandler = new FinderHandler();
        if (args.length == 0) {
            System.out.println("Enter command:");
            String inputStr = "";
            Scanner in = new Scanner(System.in);
            while (!inputStr.equals(END_OF_WORK)) {
                inputStr = in.nextLine();
                if (!inputStr.equals(END_OF_WORK)) {
                    finderHandler.doCommand(inputStr);
                }
            }
        } else {
            System.out.println(args.length);
            finderHandler.doCommand(args);
        }
    }
}
