package com.techelevator;


import java.io.*;
import java.util.List;
import java.util.Scanner;

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

    public List<Snack> inventoryFiles() {
        // Create the empty List of Snacks

        // This will take the 'in' Scanner, and parse to create & return a List of Snacks
        try {
            while (in.hasNextLine()) {
                String[] array = in.nextLine().split("\\|");
                System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        // Return the complete List of Snacks
        return null;
    }

}
