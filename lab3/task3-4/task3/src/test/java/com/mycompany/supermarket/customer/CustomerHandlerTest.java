package com.mycompany.supermarket.customer;

import com.mycompany.supermarket.cashDesk.bill.Bill;
import com.mycompany.supermarket.product.Product;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class CustomerHandlerTest {

    @Test
    public void addProductToBasket() {
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        Assert.assertEquals(1, customer.getBasket().getProducts().size());
        product = new Product("doshik", 100000.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        Assert.assertEquals(1, customer.getBasket().getProducts().size());
    }

    @Test
    public void layOutProductFromBasket() {
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        customerHandler.layOutProductFromBasket(new CustomerHandler.RemoveProductListener() {
            @Override
            public void onRemoveProductFromBusker(Product product, int amount) {

            }
        });
        Assert.assertEquals(0, customer.getBasket().getProducts().size());
        customerHandler.layOutProductFromBasket(new CustomerHandler.RemoveProductListener() {
            @Override
            public void onRemoveProductFromBusker(Product product, int amount) {

            }
        });
        Assert.assertEquals(0, customer.getBasket().getProducts().size());
    }

    @Test
    public void layOutProductFromBasket1() {
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });

        customerHandler.layOutProductFromBasket(new Pair<>(product, 1), new CustomerHandler.RemoveProductListener() {
            @Override
            public void onRemoveProductFromBusker(Product product, int amount) {

            }
        });
        Assert.assertEquals(0, customer.getBasket().getProducts().size());
    }

    @Test
    public void returnAllProductFromBasket() {
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        customerHandler.returnAllProductFromBasket(new CustomerHandler.RemoveProductListener() {
            @Override
            public void onRemoveProductFromBusker(Product product, int amount) {

            }
        });
        Assert.assertEquals(0, customer.getBasket().getProducts().size());

    }

    @Test
    public void payForProducts() {
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        List<Pair<Product, Integer>> products = new ArrayList<>();
        products.add(new Pair<>(product, 1));
        Bill bill = new Bill(products, 100.0);
        customerHandler.payForProducts(bill);
        Assert.assertEquals(0, customer.getBasket().getProducts().size());
    }
}