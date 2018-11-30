package com.mycompany.supermarket.basket;

import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.product.discount.Discount;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class BasketTest {

    @Test
    public void addNewProductToBasket() {
        Product product = new Product("doshik", 345.0, false);
        Basket basket = new Basket();
        basket.addNewProductToBasket(product, "Retired", 1);
        basket.addNewProductToBasket(product, "Child", 1);
        Assert.assertEquals(2, basket.getProducts().size());
    }

    @Test
    public void removeProductFromBasket() {
        Product product = new Product("doshik", 345.0, false);
        Basket basket = new Basket();
        basket.addNewProductToBasket(product, "Child", 1);
        Assert.assertEquals(false, basket.removeProductFromBasket("Pechen"));
        Assert.assertEquals(true, basket.removeProductFromBasket("doshik"));
    }

    @Test
    public void removeProduct() {
        Product product = new Product("doshik", 100.0, false);
        Basket basket = new Basket();
        Discount discount = new Discount(0.1);
        product.setDiscount(discount);
        basket.addNewProductToBasket(product, "Child", 1);
        basket.addNewProductToBasket(product, "Retired", 1);
        Assert.assertEquals((long) 190, (long) basket.getTotalPrice());
        basket.removeProduct(new Pair<>(product, 1), "Retired");
        Assert.assertEquals((long) 100, (long) basket.getTotalPrice());
        basket.removeProduct(new Pair<>(product, 1), "Child");
        Assert.assertEquals(0, basket.getProducts().size());
    }

    @Test
    public void isEnoughMoney() {
        Basket basket = new Basket();
        Assert.assertFalse(basket.isEnoughMoney(1000.0, 10));
        Assert.assertTrue(basket.isEnoughMoney(1.0, 10));
    }

    @Test
    public void getTotalPrice() {
        Product product = new Product("doshik", 345.0, false);
        Basket basket = new Basket();
        basket.addNewProductToBasket(product, "Child", 1);
        Assert.assertEquals((long) 345.0, (long) basket.getTotalPrice());
    }

    @Test
    public void getAmountProductInBasket() {
        Product product = new Product("doshik", 345.0, false);
        Basket basket = new Basket();
        basket.addNewProductToBasket(product, "Child", 1);
        Assert.assertEquals(1, basket.getAmountProductInBasket());
    }
}