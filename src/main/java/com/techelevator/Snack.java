package com.techelevator;

import java.math.BigDecimal;

public abstract class Snack {
    private int quantity;
    private BigDecimal price;
    private String sound;
    private String location;

    public int getQuantity() {
        return quantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getSound() {
        return sound;
    }

    public String getLocation() {
        return location;
    }

    public Snack(int quantity, BigDecimal price, String location, String sound) {
        this.quantity = quantity;
        this.price = price;
        this.location = location;
        this.sound = sound;
    }
}
