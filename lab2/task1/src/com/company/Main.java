package com.company;

public class Main {

    public static void main(String[] args) {
        System.out.println(getNetAdress("192.168.1.2", "255.255.254.0"));

    }

    private static String getNetAdress(String ipAdress, String mask) {
        String result = "";
        int[] ipAdressIntArray = getIntArrayFromStringIp(ipAdress);
        int[] maskIntArray = getIntArrayFromStringIp(mask);
        if (ipAdressIntArray == null || maskIntArray == null) {
            return "";
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
                intArray[i] = Integer.parseInt(arrayStr[i]);
                if (!(intArray[i] >= 0 && intArray[i] <= 255)) {
                    return null;
                }
            }
            return intArray;
        } catch (NumberFormatException e) {
            return null;
        }
    }
}
