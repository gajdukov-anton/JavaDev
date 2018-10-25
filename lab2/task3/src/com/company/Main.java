package com.company;

public class Main {

    public static void main(String[] args) {
        if (checkInputArgumentsForCorrectness(args)) {
            System.out.println(generatePassword(Integer.parseInt(args[0]), args[1]));
        }
    }

    private static String generatePassword(int length, String alphabet) {
        String result = "";
        for (int i = 0; i < length; i++) {
            result += alphabet.charAt((int) (Math.random() * alphabet.length()));
        }
        return result;
    }
    private static boolean checkInputArgumentsForCorrectness(String[] args) {
        if (args.length != 2) {
            System.out.println("Incorrect amount of arguments. Input arguments must be 2");
            return false;
        }
        try{
            Integer.parseInt(args[0]);
        } catch (NumberFormatException e) {
            System.out.println("Incorrect key");
            return false;
        }
        if (args[1].length() == 0) {
            System.out.println("Incorrect length of password");
            return false;
        }
        return true;
    }
}
