package com.mycompany.supermarket;

import com.mycompany.supermarket.cashDesk.CashDesk;
import com.mycompany.supermarket.customer.Customer;
import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.time.Time;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Supermarket {
    private List<Customer> customers;
    private List<Pair<Product, Integer>> products;
    private boolean isOpen;
    private CashDesk cashDesk;
    private String startTime = "8:30";
    private String finishTime = "21:30";


    public Supermarket() {
        this.customers = new ArrayList<>();
        this.products = new ArrayList<>();
        this.cashDesk = new CashDesk();
    }

    public void open(List<Pair<Product, Integer>> products) {
        this.products = products;
        this.isOpen = true;
    }

    public void close() {
        isOpen = false;
        products.clear();
    }

    public boolean canBeOpen(Time time) {
        return startTime.equals(time.getCurrentTime());
    }

    public boolean canBeClose(Time time) {
        return finishTime.equals(time.getCurrentTime());
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public void setProducts(List<Pair<Product, Integer>> products) {
        this.products = products;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public boolean isOpen() {
        return this.isOpen;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return products;
    }

    public int getIdProduct(Product product) {
        for (int i = 0; i < products.size(); i++) {
            if (product.equals(products.get(i).getKey())) {
                return i;
            }
        }
        return -1;
    }

    public CashDesk getCashDesk() {
        return this.cashDesk;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getFinishTime() {
        return finishTime;
    }
}
