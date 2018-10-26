package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(getNetAdress(args[0], args[1]));
    }

    private static String getNetAdress(String ipAdress, String mask) {
        String result = "";
        int[] ipAdressIntArray = getIntArrayFromStringIp(ipAdress);
        int[] maskIntArray = getIntArrayFromStringIp(mask);
        if (ipAdressIntArray == null) {
            return "Wrong ip address";
        }
        if (maskIntArray == null ||!isMask(maskIntArray)) {
            return "Wrong mask";
        }
        for (int i = 0; i < ipAdressIntArray.length; i++) {
            result += String.valueOf(ipAdressIntArray[i] & maskIntArray[i]) + '.';
        }
        return result.substring(0, result.length() - 1);
    }

    private static int[] getIntArrayFromStringIp(String ipStr) {
        if (ipStr == null || ipStr.isEmpty() || ipStr.endsWith(".")) {
            return null;
        }
        try {
            String[] arrayStr = ipStr.split("\\.");
            if (arrayStr.length != 4) {
                return null;
            }
            int[] intArray = new int[arrayStr.length];
            for (int i = 0; i < arrayStr.length; i++) {
                intArray[i] = Integer.valueOf(arrayStr[i]);
                System.out.println(arrayStr[i] + " " + intArray[i]);
                if (!(intArray[i] >= 0 && intArray[i] <= 255) || arrayStr[i].charAt(0) == '-') {
                    return null;
                }
            }
            return intArray;
        } catch (NumberFormatException e) {
            return null;
        }
    }

    private static boolean isMask(int[] maskArray) {
        int mask = 128;
        for(int number : maskArray) {
            int currentNumber = number;

           for (int i = 0; i < 8; i++) {
                //System.out.println(i + " " +currentNumber + " " + mask + " " + (currentNumber & mask));
                if ((currentNumber & 128) != 0 && mask == 0) {
                    //System.out.println(currentNumber + " " + mask + " " + (currentNumber & 1));
                    return false;
                }
                if ((currentNumber & mask) != mask) {
                    mask = 0;
                }
                currentNumber <<=  1;
            }
        }
        return true;
    }
}
