package edu.brown.cs.student.main;

import edu.brown.cs.student.main.parser.MyParser;
import edu.brown.cs.student.main.rowHandler.RowHandler;
import java.io.*;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

/**
 * The Main class of our project. This is where execution begins.
 */
public final class Main {

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) {
    new Main(args).run();
  }

  private Main(String[] args) {
  }

  private void run() {
    Scanner myScan = new Scanner(System.in);
    System.out.println(
        "Welcome to the CSV parser. Please provide the path to the file (For example, "
            + "data/census/income_by_race_edited.csv, if the file is in the working directory)");
    String path = myScan.nextLine();
    System.out.println("Does your file have a header row? (Y/N)");
    String headerAns = myScan.nextLine();
    boolean header = headerAns.toLowerCase().equals("y");
    try {
      FileReader myReader = new FileReader(path);
      RowHandler row = new RowHandler();
      MyParser parser = new MyParser(myReader, header, row);
    } catch (FileNotFoundException e) {
      System.err.println(e);
      this.run();
    } catch (IOException e) {
      System.err.println(e);
    }
    System.out.println("What is the word we are looking for?");
    String searchWord = myScan.nextLine();
    System.out.println("Is there a colum specifier? (Y/N) (Hit enter if none)");
    String specifier = myScan.nextLine();
    if (!specifier.isEmpty()) {
      if (header) {
        System.out.println("Please enter the name of the column to look for");
      } else {
        System.out.println("Please enter the index of the column to look for");
      }
      specifier = myScan.nextLine();
      //call the searcher with
    } else {
      //call the searcher without
    }
  }
}
