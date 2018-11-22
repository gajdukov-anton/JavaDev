package com.company.supermarket.cashDesk.paymentMethod;

import com.company.supermarket.cashDesk.bill.Bill;
import com.company.supermarket.customer.Customer;
import com.company.supermarket.info.message.Message;

import java.util.ArrayList;
import java.util.List;

public class PaymentMethod {
    private static String byCard = "card";
    private static String byCash = "cash";
    private static String byBonus = "bonus";

    public static void pay(Customer customer, Bill bill) {
        double totalPrice = bill.getTotalPrice();
        List<String> paymentMethods = new ArrayList<>();
        double cash = customer.getCash();
        double bonus = customer.getBonus();
        double cashOnCard = customer.getCashOnCard();
        if (bonus >= totalPrice) {
            customer.setBonus(bonus - totalPrice);
            paymentMethods.add(byBonus);
        } else if ((bonus + cash) >= totalPrice) {
            customer.setBonus(0);
            customer.setCash((bonus + cash) - totalPrice);
            paymentMethods.add(byBonus);
            paymentMethods.add(byCash);
        } else {
            customer.setBonus(0);
            customer.setCash(0);
            customer.setCashOnCard((bonus + cash + cashOnCard) - totalPrice);
            paymentMethods.add(byBonus);
            paymentMethods.add(byCard);
            paymentMethods.add(byCard);
        }
        Message.createMessageForPayForProducts(customer, paymentMethods, bill);
    }
}
