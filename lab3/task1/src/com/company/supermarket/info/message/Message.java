package com.company.supermarket.info.message;

import com.company.supermarket.basket.Basket;
import com.company.supermarket.cashDesk.bill.Bill;
import com.company.supermarket.customer.Customer;
import com.company.supermarket.product.discount.Discount;
import com.company.supermarket.product.Product;
import javafx.util.Pair;

import java.util.List;

public class Message {


    public static void createMessageForAddNewProductInSupermarket(Product product) {
        System.out.println("Added new product in supermarket");
        System.out.println("Title: " + product.getTitle());
        System.out.println("Price: " + product.getPrice());
        System.out.println("");
    }

    public static void createMessageForRemoveProductFromSupermarket(String title) {
        System.out.println("Removed product from supermarket");
        System.out.println("Title: " + title);
        System.out.println("");
    }

    public static void createMessageForAddNewCustomer(Customer customer) {
        System.out.println("Added new customer");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Total cash: " + customer.getAllMoney());
        System.out.println("");
    }

    public static void createMessageForRemoveCustomer(String name, String lastName, int amountCustomer) {
        System.out.println("Removed new customer");
        System.out.println("Name: " + name);
        System.out.println("Last name: " + lastName);
        System.out.println("Amount customers: " + amountCustomer);
        System.out.println("");
    }

    public static void createMessageForAddProductToBasket(Product product, Basket basket, Customer customer) {
        System.out.println("Added product to basket");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Title: " + product.getTitle());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Total price: " + basket.getTotalPrice());
        System.out.println("");
    }

    public static void createMessageForNoProductInSupermarket(Product product, Customer customer) {
        System.out.println("Customer wanted to add product to basket, but product ended");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Title: " + product.getTitle());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("");
    }

    public static void createMessageForAttemptToAddProductToBasket(Product product, Basket basket, Customer customer) {
        System.out.println("Customer wanted to add product to basket, but he has not enough money");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Title: " + product.getTitle());
        System.out.println("Product price: " + product.getPrice());
        System.out.println("Total price: " + basket.getTotalPrice());
        System.out.println("");
    }

    public static void createMessageForRemoveProductFromBasket(String title, Basket basket, Customer customer) {
        System.out.println("Removed product from basket");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Title: " + title);
        System.out.println("Total price: " + basket.getTotalPrice());
        System.out.println("Product amount in basket: " + basket.getAmountProductInBasket());
        System.out.println("");
    }

    public static void createMessageForSetDiscount(Discount discount, Product product) {
        System.out.println("Set discount for product");
        System.out.println("Title: " + product.getTitle());
        System.out.println("Discount " + discount.getValue());
        System.out.println("");
    }

    public static void createMessageForReturnCustomerFromCashDesk(Customer customer, int queueSize) {
        System.out.println("Customer went out from queue");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Customer in queue: " + queueSize);
        System.out.println("");

    }

    public static void createMessageForAddCustomerInQueue(Customer customer, int queueSize) {
        System.out.println("Customer went to cash desk");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Customer in queue: " + queueSize);
        System.out.println("");
    }

    public static void createMessageForPayForProducts(Customer customer, List<String> paymentMethods, Bill bill) {
        System.out.println("Customer paid for products");
        System.out.println("Name: " + customer.getName());
        System.out.println("Last name: " + customer.getLastName());
        System.out.println("Status: " + customer.getStatus());
        System.out.println("Total price: " + bill.getTotalPrice());
        System.out.println("Payment Methods: " );
        for (String method : paymentMethods) {
            System.out.println(method);
        }
        System.out.println("");
    }

    public static void createMessageForOpen() {
        System.out.println("Supermarket open");
    }

    public static void createMessageForClose() {
        System.out.println("Supermarket close");
    }

    public static void createMessageForLackOfProducts() {
        System.out.println("Products are no more");
    }
}
