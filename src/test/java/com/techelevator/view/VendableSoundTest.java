package com.techelevator.view;

import com.techelevator.CandySnack;
import com.techelevator.ChipSnack;
import com.techelevator.DrinkSnack;
import com.techelevator.GumSnack;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;

public class VendableSoundTest {

    CandySnack candySnack = new CandySnack("Candy Snack", BigDecimal.valueOf(4.56), "D4");
    ChipSnack chipSnack = new ChipSnack("Chip Snack", BigDecimal.valueOf(1.23), "A1");
    DrinkSnack drinkSnack = new DrinkSnack("Drink Snack", BigDecimal.valueOf(2.34), "B2");
    GumSnack gumSnack = new GumSnack("Gum Snack", BigDecimal.valueOf(3.45), "C3");


    @Test
    public void testCandySnack() {
        assertEquals("Candy Snack", candySnack.getName());
        assertEquals(BigDecimal.valueOf(4.56), candySnack.getPrice());
        assertEquals("D4", candySnack.getLocation());
        assertEquals("Munch Munch, Mmm-Good!", candySnack.getSound());
        assertEquals(5, candySnack.getQuantity());
    }

    @Test
    public void testChipSnack() {
        assertEquals("Chip Snack", chipSnack.getName());
        assertEquals(BigDecimal.valueOf(1.23), chipSnack.getPrice());
        assertEquals("A1", chipSnack.getLocation());
        assertEquals("Crunch Crunch, Crunch!", chipSnack.getSound());
        assertEquals(5, chipSnack.getQuantity());
    }

    @Test
    public void testDrinkSnack() {
        assertEquals("Drink Snack", drinkSnack.getName());
        assertEquals(BigDecimal.valueOf(2.34), drinkSnack.getPrice());
        assertEquals("B2", drinkSnack.getLocation());
        assertEquals("Cheers Glug, Glug!", drinkSnack.getSound());
        assertEquals(5, drinkSnack.getQuantity());
    }

    @Test
    public void testGumSnack() {
        assertEquals("Gum Snack", gumSnack.getName());
        assertEquals(BigDecimal.valueOf(3.45), gumSnack.getPrice());
        assertEquals("C3", gumSnack.getLocation());
        assertEquals("Chew Chew, Pop!", gumSnack.getSound());
        assertEquals(5, gumSnack.getQuantity());
    }
}
