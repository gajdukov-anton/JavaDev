package com.company.supermarket.cashDesk;

import com.company.supermarket.cashDesk.bill.Bill;
import com.company.supermarket.customer.Customer;
import com.company.supermarket.info.message.Message;
import com.company.supermarket.product.Product;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;

public class CashDesk {
    private List<Pair<Customer, Integer>> queue;

    public CashDesk() {
        this.queue = new ArrayList<>();
    }

    public void addCustomer(Customer customer) {
        this.queue.add(new Pair<>(customer, 3));
        Message.createMessageForAddCustomerInQueue(customer, this.queue.size());
    }

    public interface Callback {
        void onUserWentOutFromCashDesk(Customer customer);

        void onCashDeskCreateBill(Customer customer, Bill bill);

        void onCustomerGetProhibitedProduct(Customer customer, List<Pair<Product, Integer>> prohibitedProducts);
    }

    public interface CloseListener {
        void onCloseCashDesk(List<Customer> customers);
    }

    public void updateQueue(Callback callback) {
        if (!this.queue.isEmpty()) {
            if (this.queue.get(0).getValue() == 1) {
                Customer customer = this.queue.get(0).getKey();
                checkProductsInBasket(customer, callback);
                Bill bill = new Bill(customer.getBasket().getProducts(), customer.getBasket().getTotalPrice());
                callback.onCashDeskCreateBill(customer, bill);
                this.queue.remove(0);
                Message.createMessageForReturnCustomerFromCashDesk(customer, this.queue.size());
                callback.onUserWentOutFromCashDesk(customer);
            } else {
                Customer customer = this.queue.get(0).getKey();
                int amountTime = this.queue.get(0).getValue() - 1;
                this.queue.set(0, new Pair<>(customer, amountTime));
            }
        }
    }

    public void closeCashDesk(CloseListener closeListener) {
        List<Customer> customers = new ArrayList<>();
        for (int i = 0; i < queue.size(); i++) {
            customers.add(queue.get(i).getKey());
        }
        closeListener.onCloseCashDesk(customers);
        queue.clear();
    }

    private void checkProductsInBasket(Customer customer, Callback callback) {
        List<Pair<Product, Integer>>  prohibitedProducts = new ArrayList<>();
        for (int i = 0; i < customer.getBasket().getProducts().size(); i++) {
            if (!customer.canTakeProduct(customer.getBasket().getProducts().get(i).getKey())) {
                prohibitedProducts.add(customer.getBasket().getProducts().get(i));
            }
        }
        callback.onCustomerGetProhibitedProduct(customer, prohibitedProducts);
    }
}
