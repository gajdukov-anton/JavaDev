package com.company.supermarket;

import com.company.supermarket.time.Time;

import java.util.Random;

public class SupermarketSimulator {
    private Supermarket supermarket;
    private SupermarketHandler supermarketHandler;
    private Time time;

    public SupermarketSimulator() {
        this.supermarket = new Supermarket();
        this.supermarketHandler = new SupermarketHandler(supermarket);
    }

    public void StartSimulator() {
        time = new Time(30, 8);
        boolean is = true;
        for (int i = 0; i < 150; i++) {
       // while (is) {
            System.out.println("Current time: " + time.getCurrentTime() + " " + String.valueOf(supermarket.isOpen()));
            supermarketHandler.openSupermarket(time);
            supermarketHandler.closeSupermarket(time);
            supermarketHandler.updateCashDesk();
            supermarketHandler.doAction();
            time.updateTime(10);
//            try {
//                Thread.sleep(1000);
//            } catch (InterruptedException e) {
//                System.err.println(e.getMessage());
//            }
        }
    }

    private void testScript1() {
        time = new Time(30, 8);
        supermarketHandler.openSupermarket(time);
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.setCustomerInCashDesk();
        supermarketHandler.updateCashDesk();
        supermarketHandler.updateCashDesk();
    }

    private void testScript2() {
        time = new Time(30, 8);
        supermarketHandler.openSupermarket(time);
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addNewCustomerInSupermarket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.addProductToBasket();
        supermarketHandler.setCustomerInCashDesk();
        supermarketHandler.setCustomerInCashDesk();
        supermarketHandler.setCustomerInCashDesk();
        supermarketHandler.updateCashDesk();
        supermarketHandler.closeSupermarket(new Time(30, 21));
//        supermarketHandler.updateCashDesk();
//        supermarketHandler.updateCashDesk();
    }

    private int getNumberOfSupermarketManager() {
        Random random = new Random();
        return random.nextInt(2);

    }

}
