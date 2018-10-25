package com.company;

public class Word {
    private String value;
    private int amount;

    public Word() {
        value = "";
        amount = 0;
    }

    public Word(String value, int amount) {
        this.amount = amount;
        this.value = value;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public int getAmount() {
        return amount;
    }

    public String getValue() {
        return value;
    }
}
