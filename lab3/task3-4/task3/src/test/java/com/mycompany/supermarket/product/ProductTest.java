package com.mycompany.supermarket.product;

import com.mycompany.supermarket.product.discount.Discount;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ProductTest {

    @Test
    public void isNotForChild() {
        Product product = new Product("doshik", 100.0, false);
        Assert.assertFalse(product.isNotForChild());
        product = new Product("beer", 100.0, true);
        Assert.assertTrue(product.isNotForChild());
    }


    @Test
    public void getDiscount() {
        Product product = new Product("doshik", 100.0, false);
        Discount discount = new Discount(0.1);
        product.setDiscount(discount);
        Assert.assertEquals(discount, product.getDiscount());
    }

    @Test
    public void getTitle() {
        Product product = new Product("doshik", 100.0, false);
        Assert.assertEquals("doshik", product.getTitle());
    }

    @Test
    public void getPrice() {
        Product product = new Product("doshik", 100.0, false);
        Assert.assertEquals((long) 100.0,(long) product.getPrice());
    }

    @Test
    public void isCountable() {
        Product product = new Product("doshik", 100.0, false);
        product.setCountable(true);
        Assert.assertTrue(product.isCountable());
        product.setCountable(false);
        Assert.assertFalse(product.isCountable());
    }

    @Test
    public void getSpecialPrice() {
        Product product = new Product("doshik", 100.0, false);
        Discount discount = new Discount(0.1);
        product.setDiscount(discount);
        Assert.assertEquals((long) 90.0, (long) product.getSpecialPrice());
    }
}