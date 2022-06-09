package com.techelevator;

import java.math.BigDecimal;

public class DrinkSnack extends Snack {

    public DrinkSnack(String name, BigDecimal price, String location) {
        super(name, price, location, "Cheers Glug, Glug!");
    }
}
