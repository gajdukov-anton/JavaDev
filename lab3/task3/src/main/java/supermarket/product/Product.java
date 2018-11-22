package com.company.supermarket.product;

import com.company.supermarket.product.discount.Discount;

public class Product {
    private String title;
    private double price;
    private Discount discount;
    private boolean isNotForChild;
    private boolean countable;

    public Product(String title, Double price, boolean isNotForChild) {
        this.title = title;
        this.price = price;
        this.isNotForChild = isNotForChild;
    }

    public boolean isNotForChild() {
        return this.isNotForChild;
    }

    public void setCountable(boolean countable) {
        this.countable = countable;
    }

    public void setDiscount(Discount discount) {
        this.discount = discount;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Discount getDiscount() {
        return discount;
    }

    public String getTitle() {
        return title;
    }

    public double getPrice() {
        return price;
    }

    public boolean isCountable  () {
        return countable;
    }

    public double getSpecialPrice() {
        if (this.discount != null) {
            System.out.println(this.discount.getValue());
            return this.price * (1.0 - this.discount.getValue());
        } else {
            return this.price;
        }
    }
}
