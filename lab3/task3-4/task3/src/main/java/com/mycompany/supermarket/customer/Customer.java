package com.mycompany.supermarket.customer;

import com.mycompany.supermarket.basket.Basket;
import com.mycompany.supermarket.product.Product;

public class Customer {
    private int id;
    private String name = "name";
    private String lastName = "lastName";
    private double cash;
    private double bonus;
    private double cashOnCard;
    private String status = "Child";
    private Basket basket;

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
