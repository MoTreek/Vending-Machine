package com.techelevator.view;

import com.techelevator.FileScanner;
import com.techelevator.GumSnack;
import com.techelevator.Vendables;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

public class SnackInputTest {

    @Test
    public void snackInputTestNumberOfRecords() {
        FileScanner fileScanner = new FileScanner("vendingmachine.csv", "log.txt");
        List<Vendables> vendablesList = fileScanner.inventoryFiles();

        Vendables vendables = vendablesList.get(0);
        assertEquals(16, vendablesList.size());
    }

    @Test
    public void snackInputTestValuesOfLastRecord() {
        FileScanner fileScanner = new FileScanner("vendingmachine.csv", "log.txt");
        List<Vendables> vendablesList = fileScanner.inventoryFiles();

        Vendables vendables = vendablesList.get(vendablesList.size()-1);
        assertEquals("D4", vendables.getLocation());
        assertEquals("Triplemint", vendables.getName());
        assertEquals(BigDecimal.valueOf(0.75), vendables.getPrice());
        assertEquals(GumSnack.class, vendables.getClass());

    }
}
