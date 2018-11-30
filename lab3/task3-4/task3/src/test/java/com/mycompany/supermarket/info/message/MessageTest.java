package com.mycompany.supermarket.info.message;

import com.mycompany.supermarket.cashDesk.bill.Bill;
import com.mycompany.supermarket.cashDesk.paymentMethod.PaymentMethod;
import com.mycompany.supermarket.customer.Customer;
import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.product.discount.Discount;
import org.junit.Test;

import static org.junit.Assert.*;

public class MessageTest {

    @Test
    public void createMessageForAddNewProductInSupermarket() {
        Product product = new Product("doshik", 100.0, false);
        Message.createMessageForAddNewProductInSupermarket(product);
    }

    @Test
    public void createMessageForRemoveProductFromSupermarket() {
        Message.createMessageForRemoveProductFromSupermarket("doshik");
    }

    @Test
    public void createMessageForAddNewCustomer() {
        Customer customer = new Customer(1);
        Message.createMessageForAddNewCustomer(customer);
    }

    @Test
    public void createMessageForRemoveCustomer() {
        Message.createMessageForRemoveCustomer("name", "lastName", 1);
    }

    @Test
    public void createMessageForAddProductToBasket() {
        Product product = new Product("doshik", 100.0, false);
        Customer customer = new Customer(1);
        Message.createMessageForAddProductToBasket(product, customer.getBasket(), customer );
    }

    @Test
    public void createMessageForNoProductInSupermarket() {
        Product product = new Product("doshik", 100.0, false);
        Customer customer = new Customer(1);
        Message.createMessageForNoProductInSupermarket(product, customer);
    }

    @Test
    public void createMessageForAttemptToAddProductToBasket() {
        Product product = new Product("doshik", 100.0, false);
        Customer customer = new Customer(1);
        Message.createMessageForAttemptToAddProductToBasket(product, customer.getBasket(), customer );
    }

    @Test
    public void createMessageForRemoveProductFromBasket() {
        Message.createMessageForRemoveProductFromSupermarket("doshik");
    }

    @Test
    public void createMessageForSetDiscount() {
        Product product = new Product("doshik", 100.0, false);
        Discount discount = new Discount(0.1);
        Message.createMessageForSetDiscount(discount, product);
    }

    @Test
    public void createMessageForReturnCustomerFromCashDesk() {
        Customer customer = new Customer(1);
        Message.createMessageForReturnCustomerFromCashDesk(customer, 1);
    }

    @Test
    public void createMessageForAddCustomerInQueue() {
        Customer customer = new Customer(1);
        Message.createMessageForAddCustomerInQueue(customer, 1);
    }

    @Test
    public void createMessageForPayForProducts() {
        Bill bill = new Bill();
        bill.setTotalPrice(1000.0);
        Customer customer = new Customer(1);
        customer.setCash(300.0);
        customer.setBonus(100.0);
        customer.setCashOnCard(1000.0);
        PaymentMethod.pay(customer, bill);
    }

    @Test
    public void createMessageForOpen() {
        Message.createMessageForOpen();
    }

    @Test
    public void createMessageForClose() {
        Message.createMessageForClose();
    }

    @Test
    public void createMessageForLackOfProducts() {
        Message.createMessageForLackOfProducts();
    }
}