package com.mycompany.supermarket;

import com.mycompany.supermarket.cashDesk.CashDesk;
import com.mycompany.supermarket.customer.Customer;
import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.time.Time;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Currency;
import java.util.List;

import static org.junit.Assert.*;

public class SupermarketTest {

    @Test
    public void open() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        Product product = new Product("doshik", 100.0, false);
        products.add(new Pair<>(product, 1));
        Supermarket supermarket = new Supermarket();
        supermarket.open(products);
        Assert.assertEquals(1, supermarket.getProducts().size());
        Assert.assertTrue(supermarket.isOpen());
    }

    @Test
    public void close() {
        Supermarket supermarket = new Supermarket();
        supermarket.close();
        Assert.assertFalse(supermarket.isOpen());

    }

    @Test
    public void canBeOpen() {
        Time time = new Time(10, 2);
        Supermarket supermarket = new Supermarket();
        Assert.assertFalse(supermarket.canBeOpen(time));
        time = new Time(30, 8);
        Assert.assertTrue(supermarket.canBeOpen(time));
    }

    @Test
    public void canBeClose() {
        Time time = new Time(30, 21);
        Supermarket supermarket = new Supermarket();
        Assert.assertTrue(supermarket.canBeClose(time));
        time = new Time(10, 10);
        Assert.assertFalse(supermarket.canBeClose(time));
    }

    @Test
    public void getCustomers() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        Product product = new Product("doshik", 100.0, false);
        products.add(new Pair<>(product, 1));
        Supermarket supermarket = new Supermarket();
        supermarket.open(products);
        Customer customer = new Customer(1);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        supermarket.setCustomers(customers);
        Assert.assertEquals(customers, supermarket.getCustomers());
    }

    @Test
    public void isOpen() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        Product product = new Product("doshik", 100.0, false);
        products.add(new Pair<>(product, 1));
        Supermarket supermarket = new Supermarket();
        supermarket.open(products);
        Assert.assertTrue(supermarket.isOpen());
    }

    @Test
    public void getProducts() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        Product product = new Product("doshik", 100.0, false);
        products.add(new Pair<>(product, 1));
        Supermarket supermarket = new Supermarket();
        supermarket.open(products);
        Customer customer = new Customer(1);
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        supermarket.setCustomers(customers);
        Assert.assertEquals(customers, supermarket.getCustomers());
    }

    @Test
    public void getIdProduct() {
        List<Pair<Product, Integer>> products = new ArrayList<>();
        Product product = new Product("doshik", 100.0, false);
        Product product1 = new Product("ponchik", 100.0, false);
        products.add(new Pair<>(product, 1));
        products.add(new Pair<>(product1, 1));

        Supermarket supermarket = new Supermarket();
        supermarket.setProducts(products);
        int id = supermarket.getIdProduct(product1);
        Assert.assertEquals(1, id);
        Product product2 = new Product("pizza", 10000000000.0, true);
        Assert.assertEquals(-1, supermarket.getIdProduct(product2));
    }

    @Test
    public void getCashDesk() {
        Supermarket supermarket = new Supermarket();
        CashDesk cashDesk = supermarket.getCashDesk();
        Assert.assertNotNull(cashDesk);
        Assert.assertEquals(0, supermarket.getCashDesk().getQueue().size());
    }

}