package com.company;

public class Main {

    public static void main(String[] args) {
        printHeader();
        printLong();
        printInteger();
        printShort();
        printByte();
        printDouble();
        printFloat();
        printChar();
    }

    private static void printHeader() {
        System.out.printf("Type");
        System.out.printf("%10s", "Min");
        System.out.printf("%28s", "Max");
        System.out.printf("%28s", "Size");
        System.out.println();
    }

    private static void printLong() {
        System.out.printf("Long");
        System.out.printf("%27d", Long.MIN_VALUE);
        System.out.printf("%27d", Long.MAX_VALUE);
        System.out.printf("%9d", Long.SIZE / 8);
        System.out.println();
    }

    private static void printInteger() {
        System.out.printf("Integer");
        System.out.printf("%15d", Integer.MIN_VALUE);
        System.out.printf("%27d", Integer.MAX_VALUE);
        System.out.printf("%18d", Integer.SIZE / 8);
        System.out.println();
    }

    private static void printShort() {
        System.out.printf("Short");
        System.out.printf("%12d", Short.MIN_VALUE);
        System.out.printf("%27d", Short.MAX_VALUE);
        System.out.printf("%23d", Short.SIZE / 8);
        System.out.println();
    }

    private static void printByte() {
        System.out.printf("Byte");
        System.out.printf("%11d", Byte.MIN_VALUE);
        System.out.printf("%27d", Byte.MAX_VALUE);
        System.out.printf("%25d", Byte.SIZE / 8);
        System.out.println();
    }

    private static void printDouble() {
        System.out.printf("Double");
        System.out.printf("%13s",  Double.toString(Double.MIN_VALUE));
        System.out.printf("%28s",  Double.toString(Double.MIN_VALUE));
        System.out.printf("%20d", Double.SIZE / 8);
        System.out.println();
    }

    private static void printFloat() {
        System.out.printf("Float");
        System.out.printf("%13s",  Float.toString(Float.MIN_VALUE));
        System.out.printf("%28s",  Float.toString(Float.MIN_VALUE));
        System.out.printf("%21d", Float.SIZE / 8);
        System.out.println();
    }

    private static void printChar() {
        System.out.printf("Char");
        System.out.printf("%8d",  (int) Character.MIN_VALUE);
        System.out.printf("%32d",   (int) Character.MAX_VALUE);
        System.out.printf("%23d", Character.SIZE / 8);
        System.out.println();
    }
}
