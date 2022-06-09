package com.techelevator;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    // VendingMachine needs an inventory (List of Snacks), input/output scanners (FileScanner)
    //  and money functions (CoinBox)
    private List<Vendables> snackInventory = new ArrayList<>();
    private FileScanner fileScanner;

    public VendingMachine() {
        // Instantiate FileScanner with two strings for input & output file
        this.fileScanner = new FileScanner("vendingmachine.csv", "log.txt");
        // Instantiate & fill List of Snacks with FileScanner method inventoryFiles
        this.snackInventory = fileScanner.inventoryFiles();
        // Instantiate CoinBox with... nothing?
    }
}
