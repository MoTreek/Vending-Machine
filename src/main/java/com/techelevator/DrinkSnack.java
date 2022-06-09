package com.techelevator;

import java.math.BigDecimal;

public class DrinkSnack extends Snack {

    public DrinkSnack(int quantity, BigDecimal price, String location) {
        super(quantity, price, location, "Cheers Glug, Glug!");
    }
}
