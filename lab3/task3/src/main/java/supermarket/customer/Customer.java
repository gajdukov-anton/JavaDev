package com.company.supermarket.customer;

import com.company.supermarket.basket.Basket;
import com.company.supermarket.product.Product;

public class Customer {
    private int id;
    private String name;
    private String lastName;
    private double cash;
    private double bonus;
    private double cashOnCard;
    private String status;
    private Basket basket;

//    public Customer(String name, String lastName, String status) {
//        this.name = name;
//        this.lastName = lastName;
//        this.status = status;
//        this.basket = new Basket();
//        this.cash = Math.random() * 1000;
//    }

    public Customer(int id) {
        this.id = id;
        this.basket = new Basket();
        this.cash = Math.random() * 1000;
        this.bonus = Math.random() * 100;
        this.bonus = Math.random() * 300;
    }

    public boolean canTakeProduct(Product product) {
        if (product.isNotForChild() && this.status.equals("Child")) {
            return false;
        }
        return true;
    }

    public void setBonus(double bonus) {
        this.bonus = bonus;
    }

    public void setCashOnCard(double cashOnCard) {
        this.cashOnCard = cashOnCard;
    }

    public void setCash(double cash) {
        this.cash = cash;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getName() {
        return name;
    }

    public String getLastName() {
        return lastName;
    }

    public double getCash() {
        return cash;
    }

    public String getStatus() {
        return status;
    }

    public Basket getBasket() {
        return this.basket;
    }

    public int getId() {
        return id;
    }

    public double getBonus() {
        return bonus;
    }

    public double getCashOnCard() {
        return cashOnCard;
    }

    public double getAllMoney() {
        return bonus + cash + cashOnCard;
    }
}
