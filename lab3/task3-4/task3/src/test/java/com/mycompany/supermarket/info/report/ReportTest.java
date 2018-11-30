package com.mycompany.supermarket.info.report;

import com.mycompany.supermarket.product.Product;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class ReportTest {

    @Test
    public void addProduct() {
        Report report = new Report();
        Product product = new Product("doshik", 10.0, true);
        report.addProduct(product, 1);
        report.addProduct(product, 1);
        Assert.assertEquals(1, report.getProducts().size());
        Assert.assertEquals(new Integer(2), report.getProducts().get(product.getTitle()));

    }

    @Test
    public void increaseCustomers() {
        Report report = new Report();
        report.increaseCustomers();
        Assert.assertEquals(1, report.getAmountCustomers());
        report.increaseCustomers();
        Assert.assertEquals(2, report.getAmountCustomers());
    }

    @Test
    public void showReport() {
        Report report = new Report();
        Product product = new Product("doshik", 10.0, true);
        report.addProduct(product, 1);
        report.addProduct(product, 1);
        report.showReport();
    }
}