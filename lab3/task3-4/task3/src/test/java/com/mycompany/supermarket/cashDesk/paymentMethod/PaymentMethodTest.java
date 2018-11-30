package com.mycompany.supermarket.cashDesk.paymentMethod;

import com.mycompany.supermarket.cashDesk.bill.Bill;
import com.mycompany.supermarket.customer.Customer;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class PaymentMethodTest {

    @Test
    public void pay() {
        Bill bill = new Bill();
        bill.setTotalPrice(1000.0);
        Customer customer = new Customer(1);
        customer.setCash(300.0);
        customer.setBonus(100.0);
        customer.setCashOnCard(1000.0);
        PaymentMethod.pay(customer, bill);
        Assert.assertEquals((long) 400.0, (long) customer.getAllMoney());
    }
}