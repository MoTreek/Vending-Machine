package com.techelevator;


import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class FileScanner {
    private PrintWriter out;
    private Scanner in;

    public FileScanner(InputStream input, OutputStream output) {
        this.out = new PrintWriter(output);
        this.in = new Scanner(input);
    }

    public FileScanner() {

    }

    public List<Snack> inventoryFiles(){
        try{
            while(in.hasNextLine()){
                String[] array = in.nextLine().split("\\|");
                System.out.println(array[0] + " " + array[1] + " " + array[2] + " " + array[3]);
            }
        } catch (Exception e) {
            System.out.println("Exception " + e.getMessage());
        }
        return null;
    }

}
