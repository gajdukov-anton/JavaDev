package com.company;


import java.io.IOException;

public class Main {

    public static void main(String[] args)  {
        if (checkInputArgumentsForCorrectness(args)) {
            try {
                WordsFinder wordsFinder = new WordsFinder(args[0], Integer.parseInt(args[1]));
                wordsFinder.showListWithWords();
            } catch (IOException e) {
                System.out.println("The specified file does not exist");
            }
        }
    }

    private static boolean checkInputArgumentsForCorrectness(String[] args) {
        if (args.length < 2) {
            System.out.println("Incorrect amount of input arguments. There are must be 3");
            return false;
        }
        try{
            Integer.parseInt(args[1]);
        }catch (NumberFormatException e) {
            System.out.println("Incorrect amount of words. There is must be a number");
            return false;
        }
        return true;
    }
}
