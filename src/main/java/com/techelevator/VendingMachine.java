package com.techelevator;

import com.techelevator.view.Menu;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class VendingMachine {
    // VendingMachine needs an inventory (List of Snacks), input/output scanners (FileScanner)
    //  and money functions (CoinBox)
    private List<Vendables> snackInventory = new ArrayList<>();
    private FileScanner fileScanner;
    private MoneyBox moneyBox;
    // Should we make a keyboard scanner here & pass it around to the methods?

    public VendingMachine() {
        // Instantiate FileScanner with two strings for input & output file
        this.fileScanner = new FileScanner("vendingmachine.csv", "log.txt");
        // Instantiate & fill List of Snacks with FileScanner method inventoryFiles
        this.snackInventory = fileScanner.inventoryFiles();
        // Instantiate CoinBox with... nothing!
        this.moneyBox = new MoneyBox();
    }

    public void getInventory() {
        for (Vendables vendable : snackInventory) {
            System.out.println(vendable.getLocation() + " " + vendable.getName() + " " + vendable.getPrice() + " " + vendable.getQuantity());
        }
    }

    public void feedMoney(Menu menu) {
        //Tried to make a scanner, but it bombed the whole menu. Copied and modified userInput method from menu and pass it in.
        int inputFromKeyboard = -1;

        try {
            // Loop --

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
            }
        } catch (InputMismatchException imex) {
            System.out.println(inputFromKeyboard + " is not a valid dollar bill...");
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

    }

    public void selectProduct(Menu menu) {
        String userInput = menu.getStringFromUserInput();
        Vendables selectedProduct = null;

        for (Vendables vendable : snackInventory ) {
            if(userInput == vendable.getLocation()) {
                selectedProduct = vendable;
                break;
            }
        }

        if(selectedProduct != null) {
            // If balance is GTE price then vend vendable (decrease quantity & remove price from moneybox)
            if(moneyBox.getMoneyHeld().compareTo(selectedProduct.getPrice()) >= 0) {
                // vend vendable
                if(selectedProduct.isAvailable()) {
                    selectedProduct.purchaseSnack();
                    System.out.println(selectedProduct.getSound());
                }
                else {
                    System.out.println("That item is sold out...");
                }
            }
            else {
                System.out.println("The " + selectedProduct.getName() + " costs " + selectedProduct.getPrice() + " and you have insufficient funds...");
            }
        }
        else {
            System.out.println("That's not a valid item location...");
        }
    }
}
