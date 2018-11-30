package com.mycompany.supermarket.cashDesk;

import com.mycompany.supermarket.cashDesk.bill.Bill;
import com.mycompany.supermarket.customer.Customer;
import com.mycompany.supermarket.customer.CustomerHandler;
import com.mycompany.supermarket.product.Product;
import javafx.util.Pair;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class CashDeskTest {

    @Test
    public void addCustomer() {
        CashDesk cashDesk = new CashDesk();
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        cashDesk.addCustomer(customer);
        Assert.assertEquals(1, cashDesk.getQueue().size());
    }

    @Test
    public void updateQueue() {
        CashDesk cashDesk = new CashDesk();
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        customer.setStatus("Child");
        Product product = new Product("doshik", 100.0, true);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        Customer customer1 = new Customer(2);
        CustomerHandler customerHandler1 = new CustomerHandler(customer);
        customer.setCash(1000);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        cashDesk.addCustomer(customer1);
        cashDesk.addCustomer(customer);
        Assert.assertEquals(2, cashDesk.getQueue().size());
        updateQueue(cashDesk);
        updateQueue(cashDesk);
        updateQueue(cashDesk);
        Assert.assertEquals(1, cashDesk.getQueue().size());
        updateQueue(cashDesk);
        updateQueue(cashDesk);
        updateQueue(cashDesk);
        Assert.assertEquals(0, cashDesk.getQueue().size());
    }

    private void updateQueue(CashDesk cashDesk) {
        cashDesk.updateQueue(new CashDesk.Callback() {
            @Override
            public void onUserWentOutFromCashDesk(Customer customer) {

            }

            @Override
            public void onCashDeskCreateBill(Customer customer, Bill bill) {

            }

            @Override
            public void onCustomerGetProhibitedProduct(Customer customer, List<Pair<Product, Integer>> prohibitedProducts) {

            }
        });
    }

    @Test
    public void closeCashDesk() {
        CashDesk cashDesk = new CashDesk();
        Customer customer = new Customer(1);
        CustomerHandler customerHandler = new CustomerHandler(customer);
        customer.setCash(1000);
        Product product = new Product("doshik", 100.0, false);
        customerHandler.addProductToBasket(product, 1, new CustomerHandler.AddProductListener() {
            @Override
            public void onAddProductToBasket(Product product, int amount) {

            }
        });
        cashDesk.addCustomer(customer);
        cashDesk.closeCashDesk(new CashDesk.CloseListener() {
            @Override
            public void onCloseCashDesk(List<Customer> customers) {
                Assert.assertEquals(1, customers.size());
            }
        });
        Assert.assertEquals(0, cashDesk.getQueue().size());
    }
}