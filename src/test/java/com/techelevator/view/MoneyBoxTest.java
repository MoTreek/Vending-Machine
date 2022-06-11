package com.techelevator.view;

import com.techelevator.MoneyBox;
import org.junit.Assert;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MoneyBoxTest {

    @Test
    public void MoneyBox_InitialValueTest_EqualToZero(){
        MoneyBox moneyBox = new MoneyBox();

        assertEquals(new BigDecimal(0), moneyBox.getMoneyHeld());
    }

    @Test
    public void MoneyBox_FeedMoney_IncreaseAmount(){
        MoneyBox moneyBox = new MoneyBox();
        moneyBox.addMoney(BigDecimal.valueOf(10));
        assertEquals(new BigDecimal(10), moneyBox.getMoneyHeld());
    }

    @Test
    public void MoneyBox_FeedMoney_DecreaseAmount(){
        MoneyBox moneyBox = new MoneyBox();
        moneyBox.addMoney(BigDecimal.valueOf(10));
        moneyBox.subtractMoney(BigDecimal.valueOf(5));
        assertEquals(new BigDecimal(5), moneyBox.getMoneyHeld());
    }

    @Test
    public void MoneyBox_FeedMoney_ResetToZero(){
        MoneyBox moneyBox = new MoneyBox();
        moneyBox.addMoney(BigDecimal.valueOf(10));
        moneyBox.makeChange();
        BigDecimal bd = BigDecimal.valueOf(0.00);
//        assertEquals(new BigDecimal(0.00), moneyBox.getMoneyHeld());
        assertTrue(bd.compareTo(moneyBox.getMoneyHeld()) == 0);
    }
//Assert.assertArrayEquals(expected, actual);
}
