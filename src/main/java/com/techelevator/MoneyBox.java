package com.techelevator;

import java.math.BigDecimal;

public class MoneyBox {
    private final BigDecimal VALUE_OF_DOLLAR = BigDecimal.ONE;
    private final BigDecimal VALUE_OF_QUARTER = BigDecimal.valueOf(0.25);
    private final BigDecimal VALUE_OF_DIME = BigDecimal.valueOf(0.1);
    private final BigDecimal VALUE_OF_NICKEL = BigDecimal.valueOf(0.05);
    private final BigDecimal VALUE_OF_PENNY = BigDecimal.valueOf(0.01);

    BigDecimal moneyHeld = BigDecimal.ZERO;

    public MoneyBox() {
    }

    public BigDecimal getMoneyHeld() {
        return moneyHeld;
    }

    public BigDecimal addMoney(BigDecimal money) {
        moneyHeld = moneyHeld.add(money);
        return this.moneyHeld;
    }

    public BigDecimal subtractMoney(BigDecimal money) {
        moneyHeld = moneyHeld.subtract(money);
        return this.moneyHeld;
    }

    public void makeChange() {
        int quarters = 0;
        int dimes = 0;
        int nickels = 0;
        int pennies = 0;

        while (moneyHeld.compareTo(VALUE_OF_QUARTER) >= 0) {
            quarters++;
            subtractMoney(VALUE_OF_QUARTER);
        }
        while (moneyHeld.compareTo(VALUE_OF_DIME) >= 0) {
            dimes++;
            subtractMoney(VALUE_OF_DIME);
        }
        while (moneyHeld.compareTo(VALUE_OF_NICKEL) >= 0) {
            nickels++;
            subtractMoney(VALUE_OF_NICKEL);
        }

        String readOut = "Here your change: " + quarters + " quarters, " + dimes + " dimes, and " + nickels + " nickels.";
        System.out.println(readOut);
    }
}
