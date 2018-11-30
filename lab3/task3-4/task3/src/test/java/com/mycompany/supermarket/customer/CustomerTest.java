package com.mycompany.supermarket.customer;

import com.mycompany.supermarket.product.Product;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class CustomerTest {

    @Test
    public void canTakeProduct() {
        Product product = new Product("doshik", 100.0, false);
        Customer customer = new Customer(1);
        customer.setStatus("Adult");
        Assert.assertTrue(customer.canTakeProduct(product));
        product = new Product("beer", 100.0, true);
        Assert.assertTrue(customer.canTakeProduct(product));
        customer.setStatus("Child");
        Assert.assertFalse(customer.canTakeProduct(product));
    }

    @Test
    public void getName() {
        Customer customer = new Customer(1);
        customer.setName("name");
        Assert.assertEquals("name", customer.getName());
    }

    @Test
    public void getLastName() {
        Customer customer = new Customer(1);
        customer.setLastName("lastName");
        Assert.assertEquals("lastName", customer.getLastName());
    }

    @Test
    public void getCash() {
        Customer customer = new Customer(1);
        customer.setCash(10.0);
        Assert.assertEquals((long) 10.0, (long) customer.getCash());
    }

    @Test
    public void getStatus() {
        Customer customer = new Customer(1);
        customer.setStatus("Adult");
        Assert.assertEquals("Adult", customer.getStatus());
    }

    @Test
    public void getBasket() {
        Customer customer = new Customer(1);
        customer.setStatus("Adult");
        assertEquals(0, customer.getBasket().getProducts().size());
    }

    @Test
    public void getBonus() {
        Customer customer = new Customer(1);
        customer.setBonus(10.0);
        Assert.assertEquals((long) 10.0, (long) customer.getBonus());
    }

    @Test
    public void getCashOnCard() {
        Customer customer = new Customer(1);
        customer.setCashOnCard(10.0);
        Assert.assertEquals((long) 10.0, (long) customer.getCashOnCard());
    }

    @Test
    public void getAllMoney() {
        Customer customer = new Customer(1);
        customer.setBonus(10.0);
        customer.setCashOnCard(10.0);
        customer.setCash(10.0);
        Assert.assertEquals((long) 30.0, (long) customer.getAllMoney());
    }
}