package com.mycompany.supermarket.cashDesk.bill;

import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.time.Time;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Bill {
    private double totalPrice;
    private Time data;
    private List<Pair<Product, Integer>> products;

    public Bill() {
        this.products = new ArrayList<>();
    }

    public Bill(List<Pair<Product, Integer>> products, double totalPrice) {
        this.products = products;
        this.totalPrice = totalPrice;
    }

    public void setProducts(List<Pair<Product, Integer>> products) {
        this.products = products;
    }

    public void setData(Time data) {
        this.data = data;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return products;
    }
}
