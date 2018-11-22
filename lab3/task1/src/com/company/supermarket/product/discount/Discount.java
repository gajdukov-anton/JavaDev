package com.company.supermarket.product.discount;

public class Discount {
    private double value;

    public Discount(double value) {
        if (value < 1.0) {
            this.value = value;
        } else {
            this.value = 0;
        }
    }

    public Discount() {
        this.value = Math.random();
    }

    public void setValue(double value) {
        if (value < 1.0) {
            this.value = value;
        } else {
            this.value = 0;
        }
}

    public double getValue() {
        return value;
    }
}
