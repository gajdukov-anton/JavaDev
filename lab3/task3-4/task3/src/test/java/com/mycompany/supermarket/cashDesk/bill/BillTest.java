package com.mycompany.supermarket.cashDesk.bill;

import com.mycompany.supermarket.product.Product;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BillTest {

    @Test
    public void getTotalPrice() {
        Product product = new Product("doshik", 100.0, false);
        List<Pair<Product, Integer>> products = new ArrayList<>();
        products.add(new Pair<>(product, 10));
        Bill bill = new Bill(products, 1000.0);
        Assert.assertEquals((long) 1000.0, (long) bill.getTotalPrice());
        bill = new Bill();
        bill.setTotalPrice(1000.0);
        Assert.assertEquals((long) 1000.0, (long) bill.getTotalPrice());
    }

    @Test
    public void getProducts() {
        Product product = new Product("doshik", 100.0, false);
        List<Pair<Product, Integer>> products = new ArrayList<>();
        products.add(new Pair<>(product, 10));
        Bill bill = new Bill(products, 1000.0);
        Assert.assertEquals(products, bill.getProducts());
        bill = new Bill();
        bill.setProducts(products);
        Assert.assertEquals(products, bill.getProducts());
    }
}