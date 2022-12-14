package com.techelevator;

import com.techelevator.view.Menu;

import javax.print.attribute.standard.DateTimeAtProcessing;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class VendingMachine {
    // VendingMachine needs an inventory (List of Snacks), input/output scanners (FileScanner)
    //  and money functions (CoinBox)
    private List<Vendables> snackInventory = new ArrayList<>();
    private FileScanner fileScanner;
    private MoneyBox moneyBox;
    // Should we make a keyboard scanner here & pass it around to the methods?

    public VendingMachine() {
        // Instantiate FileScanner with two strings for input & output file
        this.fileScanner = new FileScanner("vendingmachine.csv", "src\\main\\resources\\log.txt");
        // Instantiate & fill List of Snacks with FileScanner method inventoryFiles
        this.snackInventory = fileScanner.inventoryFiles();
        // Instantiate CoinBox with... nothing!
        this.moneyBox = new MoneyBox();
    }

    public void getInventory() {
        for (Vendables vendable : snackInventory) {

            if (vendable.getQuantity() == 0) {
                System.out.println(vendable.getLocation() + " " + vendable.getName() + " ** SOLD OUT **");
            }
            else {
                System.out.println(vendable.getLocation() + " " + vendable.getName() + " " + vendable.getPrice() + " " + vendable.getQuantity());
            }
        }
    }

    public void feedMoney(Menu menu) {
        //Tried to make a scanner, but it bombed the whole menu. Copied and modified userInput method from menu and pass it in.
        int inputFromKeyboard = -1;

        try {
            // Loop --
            //writelog
            while (inputFromKeyboard != 0) {
                // Show Balance
                moneyBox.getMoneyHeld();
                // Prompt for int or 0 to exit
                System.out.println("Input value of dollar bill to insert (VALID DENOMINATIONS ONLY!)");
                System.out.print("or enter 0 to exit: ");
                inputFromKeyboard = menu.getIntFromUserInput();
                if (inputFromKeyboard != 1 && inputFromKeyboard != 5 && inputFromKeyboard != 10 && inputFromKeyboard != 20 && inputFromKeyboard != 50 && inputFromKeyboard != 100 && inputFromKeyboard != 0) {
                    throw new Exception("This machine only accepts 1, 5, 10, 20, 50 and 100 dollar bills...");
                }
                // Cast int as BigDecimal and add to balance
                BigDecimal balance = moneyBox.addMoney(BigDecimal.valueOf(inputFromKeyboard));
                System.out.println("Your new balance is " + balance);
                if (inputFromKeyboard != 0) {
                    fileScanner.writeLog(String.valueOf(LocalDateTime.now()) + "Fed Money: $" + inputFromKeyboard + " total $" + moneyBox.getMoneyHeld());
                }
            }
        } catch (InputMismatchException imex) {
            System.out.println(inputFromKeyboard + " is not a valid dollar bill...");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void salesReport() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yy_HH-mm-ss");
        String date = dateFormat.format(new Date());

        FileScanner outputSalesReport = new FileScanner("salesReport" + date + ".txt");
        BigDecimal moneyCounter = BigDecimal.ZERO;

        for (Vendables quantity : snackInventory) {
            outputSalesReport.writeLog(quantity.getName() + "|" + (5 - quantity.getQuantity()) + " ");
            moneyCounter = moneyCounter.add(quantity.getPrice().multiply(BigDecimal.valueOf(5 - quantity.getQuantity())));

        }
        outputSalesReport.writeLog("**Total** $" + moneyCounter + "\n");
    }

    public void selectProduct(Menu menu) {
        System.out.println("Enter item code: ");
        String userInput = menu.getStringFromUserInput();
        Vendables selectedProduct = null;

        for (Vendables vendable : snackInventory) {
            if (userInput.equals(vendable.getLocation())) {
                selectedProduct = vendable;
                break;
            }
        }
        // TODO: 6/10/2022 Make the product location case insensitive  
        if (selectedProduct != null) {
            // If balance is GTE price then vend vendable (decrease quantity & remove price from moneybox)
            if (moneyBox.getMoneyHeld().compareTo(selectedProduct.getPrice()) >= 0) {
                // vend vendable
                if (selectedProduct.isAvailable()) {
                    selectedProduct.purchaseSnack();
                    moneyBox.subtractMoney(selectedProduct.getPrice());
                    // TODO: 6/10/2022 Jazz up this output section, but get rid of quantity remaining? Hide it all in purchaseSnack()?
                    System.out.println(selectedProduct.getName());
                    System.out.println("$" + selectedProduct.getPrice());
                    System.out.println(selectedProduct.getSound());
                    System.out.println(selectedProduct.getQuantity());
                    System.out.println("$" + moneyBox.getMoneyHeld());


                    fileScanner.writeLog(LocalDateTime.now() + " " + selectedProduct.getName() + " " + selectedProduct.getLocation() + " " + selectedProduct.getPrice() + " " + moneyBox.getMoneyHeld());
                } else {
                    System.out.println("That item is sold out...");
                }
            } else {
                System.out.println("The " + selectedProduct.getName() + " costs " + selectedProduct.getPrice() + " and you have insufficient funds...");
            }
        } else {
            System.out.println("That's not a valid item location...");
        }
    }

    public boolean finishTransaction() {
        BigDecimal temp = moneyBox.getMoneyHeld();
        moneyBox.makeChange();
        fileScanner.writeLog(LocalDateTime.now() + "give change " + temp + " " + moneyBox.getMoneyHeld());

        return false;
    }
}
