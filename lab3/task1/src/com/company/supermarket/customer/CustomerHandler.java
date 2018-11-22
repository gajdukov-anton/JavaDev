package com.company.supermarket.customer;

import com.company.supermarket.cashDesk.bill.Bill;
import com.company.supermarket.info.message.Message;
import com.company.supermarket.cashDesk.paymentMethod.PaymentMethod;
import com.company.supermarket.product.Product;
import javafx.util.Pair;

import java.util.Random;

public class CustomerHandler {
    private Customer customer;

    public CustomerHandler(Customer customer) {
        this.customer = customer;

    }

    public interface AddProductListener {
        void onAddProductToBasket(Product product, int amount);
    }

    public interface RemoveProductListener {
        void onRemoveProductFromBusker(Product product, int amount);
    }

    public void addProductToBasket(Product product, int amount,  AddProductListener callback) {
        if (customer.getBasket().isEnoughMoney(product.getPrice(), customer.getAllMoney())) {
            customer.getBasket().addNewProductToBasket(product, customer.getStatus(), amount);
            callback.onAddProductToBasket(product, amount);
            Message.createMessageForAddProductToBasket(product, customer.getBasket(), customer);
        } else {
            Message.createMessageForAttemptToAddProductToBasket(product, customer.getBasket(), customer);
        }
    }

    public void layOutProductFromBasket(RemoveProductListener removeProductListener) {
        if (!customer.getBasket().getProducts().isEmpty()) {
            Pair<Product, Integer> product = getProductFromBasket();
            customer.getBasket().getProducts().remove(product);
            removeProductListener.onRemoveProductFromBusker(product.getKey(), product.getValue());
            Message.createMessageForRemoveProductFromBasket(product.getKey().getTitle(), customer.getBasket(), customer);
        }
    }

    public void layOutProductFromBasket(Pair<Product, Integer> product, RemoveProductListener removeProductListener) {
        if (!customer.getBasket().getProducts().isEmpty()) {
            customer.getBasket().removeProduct(product, customer.getStatus());
            removeProductListener.onRemoveProductFromBusker(product.getKey(), product.getValue());
            Message.createMessageForRemoveProductFromBasket(product.getKey().getTitle(), customer.getBasket(), customer);
        }
    }

    public void returnAllProductFromBasket(RemoveProductListener removeProductListener) {
        while (!customer.getBasket().getProducts().isEmpty()) {
            layOutProductFromBasket(removeProductListener);
        }
    }

    public void payForProducts(Bill bill) {
        if (!customer.getBasket().getProducts().isEmpty()) {
            int amountProductInBasket = customer.getBasket().getProducts().size();
            for (int i = 0; i < amountProductInBasket; i++) {
                Pair<Product, Integer> product = customer.getBasket().getProducts().get(0);
                customer.getBasket().getProducts().remove(product);
            }
            PaymentMethod.pay(customer, bill);
        }
    }

    private Pair<Product, Integer> getProductFromBasket() {
        Random random = new Random();
        int id = random.nextInt(this.customer.getBasket().getProducts().size());
        return this.customer.getBasket().getProducts().get(id);
    }
}
