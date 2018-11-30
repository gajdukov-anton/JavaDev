package com.mycompany.supermarket;

import com.mycompany.supermarket.time.Time;

import java.util.Random;

public class SupermarketSimulator {
    private Supermarket supermarket;
    private SupermarketHandler supermarketHandler;
    private Time time;

    public SupermarketSimulator() {
        this.supermarket = new Supermarket();
        this.supermarketHandler = new SupermarketHandler(supermarket);
    }

    public void StartSimulator(int amountIterations) {
        time = new Time(30, 8);
        for (int i = 0; i < amountIterations; i++) {
            System.out.println("Current time: " + time.getCurrentTime());
            supermarketHandler.openSupermarket(time);
            supermarketHandler.closeSupermarket(time);
            supermarketHandler.updateCashDesk();
            supermarketHandler.doAction();
            time.updateTime(10);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getMessage());
            }
        }
    }
}
