package com.techelevator;

import java.math.BigDecimal;

public abstract class Snack implements Vendables {
    private int quantity = 5;
    private String name;
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

    public String getName() {
        return name;
    }

    public Snack(String name, BigDecimal price, String location, String sound) {
        this.price = price;
        this.location = location;
        this.sound = sound;
        this.name = name;
    }
}
