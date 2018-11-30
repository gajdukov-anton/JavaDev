package com.mycompany.supermarket.info.report;

import com.mycompany.supermarket.product.Product;

import java.util.HashMap;
import java.util.Map;

public class Report {
    private int amountCustomers;
    private Map<String, Integer> products;
    private double revenue;

    public Report() {
        products = new HashMap<>();
    }

    public void addProduct(Product product, int amount) {
        revenue += product.getPrice() * amount;
        if (!products.containsKey(product.getTitle())) {
            products.put(product.getTitle(), amount);
        } else {
            int previousAmount = products.get(product.getTitle());
            products.replace(product.getTitle(), previousAmount + amount);
        }
    }

    public void increaseCustomers() {
        amountCustomers++;
    }

    public void showReport() {
        System.out.println("Full information for the day");
        System.out.println("Total amount customers: " + amountCustomers);
        System.out.println("Total revenue: " + revenue);
        System.out.println("Sell goods: ");
        for (Map.Entry<String, Integer> product : products.entrySet()) {
            System.out.println("Title: " + product.getKey() + " amount: " + product.getValue());
        }

    }

    public Map<String, Integer> getProducts() {
        return products;
    }

    public int getAmountCustomers() {
        return amountCustomers;
    }
}
