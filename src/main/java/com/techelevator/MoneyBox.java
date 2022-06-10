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
}
