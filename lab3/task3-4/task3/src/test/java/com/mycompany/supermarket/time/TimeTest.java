package com.mycompany.supermarket.time;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TimeTest {

    @Test
    public void getCurrentTime() {
        Time time = new Time(30, 8);
        Assert.assertEquals("8:30", time.getCurrentTime());
        time = new Time(60, 60);
        Assert.assertEquals("23:59", time.getCurrentTime());
        time = new Time(-10, -10);
        Assert.assertEquals("0:0", time.getCurrentTime());
    }

    @Test
    public void updateTime() {
        Time time = new Time(30, 8);
        time.updateTime(10);
        Assert.assertEquals("8:40", time.getCurrentTime());
        time = new Time(60, 60);
        time.updateTime(10);
        Assert.assertEquals("0:0", time.getCurrentTime());
        time = new Time(-10, -10);
        time.updateTime(10);
        Assert.assertEquals("0:10", time.getCurrentTime());
    }
}