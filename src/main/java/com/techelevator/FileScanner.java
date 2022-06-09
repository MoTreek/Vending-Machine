package com.techelevator;


import java.io.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class FileScanner {
    // 'out' will connect to the log file for... logging
    private PrintWriter out;
    // 'in' will connect to the inventory file for parsing
    private Scanner in;

    public FileScanner(String input, String output) {
        // Need to take a string & create a FileOutputStream
        this.out = getPrintWriter(output);
        // Need to take a string & create a Scanner connected to a file named String
        this.in = getScanner(input);
    }

    public Scanner getScanner(String input) {
        try {
            File inputFile = new File(input);
            return new Scanner(inputFile);
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage());
        }
        return null;
    }

    private PrintWriter getPrintWriter (String outputFile) {
        File newFile = new File(outputFile);

        try {
            if(!newFile.exists()) {
                newFile.createNewFile();
            }
        } catch (IOException ioex) {
            System.out.println(ioex.getMessage());
        }
        try (PrintWriter printWriter = new PrintWriter(new FileOutputStream(newFile, false))) {
            return printWriter;
        } catch (FileNotFoundException fnfex) {
            System.out.println(fnfex.getMessage());
        }
        return null;
    }

    public List<Vendables> inventoryFiles() {
        // Create the empty List of Snacks

        // This will take the 'in' Scanner, and parse to create & return a List of Snacks
        List<Vendables> snacksList = new ArrayList<>();
        try {
            while (in.hasNextLine()) {
                // array[0] is location, array[1] is name, array[2] is price, array[3] is type
                String[] array = in.nextLine().split("\\|");
                if (array[3].equals("Chip")){
                    //ToDO Can we collapse these declarations into one?
                    //ToDo public Snack(String name, BigDecimal price, String location, String sound)
                    ChipSnack snack = new ChipSnack(array[1], new BigDecimal(array[2]), array[0]);
                    snacksList.add(snack);
                }
                else if (array[3].equals("Candy")) {
                    CandySnack snack = new CandySnack(array[1], new BigDecimal(array[2]), array[0]);
                    snacksList.add(snack);
                }
                else if (array[3].equals("Gum")) {
                    GumSnack snack = new GumSnack(array[1], new BigDecimal(array[2]), array[0]);
                    snacksList.add(snack);
                }
                else if (array[3].equals("Drink")) {
                    DrinkSnack snack = new DrinkSnack(array[1], new BigDecimal(array[2]), array[0]);
                    snacksList.add(snack);
                }
                else {
                    throw new DataFormatException(array[3] + " isn't vendable...");
                }
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        // Return the complete List of Snacks
        return snacksList;
    }

}
