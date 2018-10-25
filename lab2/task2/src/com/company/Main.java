package com.company;

public class Main {

    public static void main(String[] args) {
        if (checkInputArgumentsForCorrectness(args)) {
            if (args[0].equals("-e")) {
                System.out.println(encode(args[2], Integer.parseInt(args[1])));
            } else {
                System.out.println(decode(args[2], Integer.parseInt(args[1])));
            }
        }
    }

    private static String encode(String str, int key) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int numberOfLetter = (int) str.charAt(i) + key % 26;
            result += getLetterFromNumber(numberOfLetter, Character.isLowerCase(str.charAt(i)));
        }
        return result;
    }

    private static String decode(String str, int key) {
        String result = "";
        for (int i = 0; i < str.length(); i++) {
            int numberOfLetter = (int) str.charAt(i) - key % 26;
            result += getLetterFromNumber(numberOfLetter, Character.isLowerCase(str.charAt(i)));
        }
        return result;
    }

    private static char getLetterFromNumber(int numberOfLetter, boolean isLoverCase) {
        if (isLoverCase) {
            if (numberOfLetter > 122) {
                return (char) (numberOfLetter - 122 + 96);
            } else if (numberOfLetter < 97) {
                return (char) (122 - (96 - numberOfLetter));
            }
        } else {
            if (numberOfLetter > 90) {
                return (char) (numberOfLetter - 90 + 64);
            } else if (numberOfLetter < 65) {
                return (char) (90 - (64 - numberOfLetter));
            }
        }
        return (char) (numberOfLetter);
    }

    private static boolean checkInputArgumentsForCorrectness(String[] args) {
        if (args.length != 3) {
            System.out.println("Incorrect amount of arguments. Input arguments must be 3");
            return false;
        }
        try{
            Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect key");
            return false;
        }
        if (!(args[0].equals("-e") || args[0].equals("-d"))) {
            System.out.println("Incorrect command");
            return false;
        }
        return true;
    }

}
