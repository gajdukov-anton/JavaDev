package com.mycompany.supermarket;

import org.junit.Test;

import static org.junit.Assert.*;

public class SupermarketSimulatorTest {

    @Test
    public void startSimulator() {
        SupermarketSimulator supermarketSimulator = new SupermarketSimulator();
        supermarketSimulator.StartSimulator(10);
    }
}