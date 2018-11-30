package com.mycompany.supermarket.basket;

import com.mycompany.supermarket.product.Product;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class Basket {
    private List<Pair<Product, Integer>> products;
    private double totalPrice = 0;

    public Basket() {
        this.products = new ArrayList<>();
    }

    public void addNewProductToBasket(Product product, String statusCustomer, int amount) {
        this.products.add(new Pair<>(product, amount));
        if (statusCustomer.equals("Retired")) {
            this.totalPrice += product.getSpecialPrice();
        } else {
            this.totalPrice += product.getPrice();
        }
    }

    public boolean removeProductFromBasket(String title) {
        for (int i = 0; i < products.size(); i++) {
            if (products.get(i).getKey().getTitle().equals(title)) {
                products.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Pair<Product, Integer>> getProducts() {
        return this.products;
    }

    public void removeProduct(Pair<Product, Integer> product, String statusCustomer) {
        if (statusCustomer.equals("Retired")) {
            this.totalPrice -= product.getKey().getSpecialPrice();
        } else {
            this.totalPrice -= product.getKey().getPrice();
        }
        products.remove(product);
    }

    public boolean isEnoughMoney(double price, double userCash) {
        return this.totalPrice + price <= userCash;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public int getAmountProductInBasket() {
        return this.products.size();
    }
}
