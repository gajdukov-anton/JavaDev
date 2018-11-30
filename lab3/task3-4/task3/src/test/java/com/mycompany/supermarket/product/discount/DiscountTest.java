package com.mycompany.supermarket.product.discount;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class DiscountTest {

    @Test
    public void getValue() {
        Discount discount = new Discount(12.0);
        Assert.assertEquals((long) 0.0, (long) discount.getValue());
        discount = new Discount(- 12.0);
        Assert.assertEquals((long) 0.0, (long) discount.getValue());
        discount = new Discount(0.4);
        Assert.assertEquals((long) 0.4, (long) discount.getValue());
    }
}