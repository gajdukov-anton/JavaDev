package com.mycompany.supermarket;

import com.mycompany.supermarket.customer.Customer;
import com.mycompany.supermarket.product.Product;
import com.mycompany.supermarket.time.Time;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.junit.Assert.*;

public class SupermarketHandlerTest {

    @Test
    public void openSupermarket() {
        Time time = new Time(30, 8);
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(time);
        Assert.assertTrue(supermarket.isOpen());
        Assert.assertTrue(supermarket.getProducts().size() > 0);
    }

    @Test
    public void closeSupermarket() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.closeSupermarket(new Time(30, 21));
        Assert.assertFalse(supermarket.isOpen());
        supermarketHandler.openSupermarket(new Time(30, 8));
        Customer customer = new Customer(1);
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.setCustomerInCashDesk();
        supermarketHandler.updateCashDesk();
        supermarketHandler.closeSupermarket(new Time(30, 21));
    }

    @Test
    public void addNewCustomerInSupermarket() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addNewCustomerInSupermarket();
        Assert.assertEquals(2, supermarket.getCustomers().size());
    }

    @Test
    public void removeCustomerFromSupermarket() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.removeCustomerFromSupermarket();
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.removeCustomerFromSupermarket();
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.removeCustomerFromSupermarket();
        supermarketHandler.removeCustomerFromSupermarket();
        Assert.assertEquals(0, supermarket.getCustomers().size());
    }

    @Test
    public void addProductToBasket() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.addProductToBasket();
        supermarketHandler.addNewCustomerInSupermarket();
        Product product = supermarketHandler.addProductToBasket();
        if (product.getPrice() > supermarket.getCustomers().get(0).getAllMoney()) {
            Assert.assertEquals(0, supermarket.getCustomers().get(0).getBasket().getProducts().size());
        } else {
            Assert.assertEquals(1, supermarket.getCustomers().get(0).getBasket().getProducts().size());
        }

    }

    @Test
    public void setCustomerInCashDesk() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.addProductToBasket();
        supermarketHandler.addNewCustomerInSupermarket();
        Product product = supermarketHandler.addProductToBasket();
        supermarketHandler.setCustomerInCashDesk();
        Assert.assertEquals(1, supermarket.getCashDesk().getQueue().size());
    }

    @Test
    public void updateCashDesk() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.addProductToBasket();
        supermarketHandler.addNewCustomerInSupermarket();
        Product product = supermarketHandler.addProductToBasket();
        int amountProduct = supermarket.getCustomers().get(0).getBasket().getProducts().size();
        supermarketHandler.setCustomerInCashDesk();
        if (amountProduct == 0) {
            Assert.assertEquals(0, supermarket.getCashDesk().getQueue().size());

        } else {
            Assert.assertEquals(1, supermarket.getCashDesk().getQueue().size());
        }
        supermarketHandler.updateCashDesk();
        supermarketHandler.updateCashDesk();
        supermarketHandler.updateCashDesk();
        Assert.assertEquals(0, supermarket.getCashDesk().getQueue().size());
    }

    @Test
    public void returnProductFromBasket() {
        Supermarket supermarket = new Supermarket();
        SupermarketHandler supermarketHandler = new SupermarketHandler(supermarket);
        supermarketHandler.openSupermarket(new Time(30, 8));
        supermarketHandler.addProductToBasket();
        supermarketHandler.addNewCustomerInSupermarket();
        Product product = supermarketHandler.addProductToBasket();
        if (product.getPrice() < supermarket.getCustomers().get(0).getAllMoney()) {
            supermarketHandler.returnProductFromBasket(supermarket.getCustomers().get(0), product, supermarket.getCustomers().get(0).getBasket().getProducts().get(0).getValue());
            Assert.assertEquals(0, supermarket.getCustomers().get(0).getBasket().getProducts().size());
        }
    }


    @Test
    public void getCustomerFromSupermarket() {
    }
}