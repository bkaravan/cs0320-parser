package edu.brown.cs.student.main;

import edu.brown.cs.student.main.parser.MyParser;
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
    System.out.println("Welcome to the CSV parser. Please provide the path to the file (For example, "
        + "data/census/income_by_race_edited.csv, if the file is in the working directory)");
    String path = myScan.nextLine();
    System.out.println("Does your file have a header row? (Y/N)");
    boolean header;
    String headerAns;
    headerAns = myScan.nextLine();
    header = headerAns.toLowerCase().equals("y");
    System.out.println("Parsing your file...");
    try {
      MyParser parser = new MyParser(path, header);
    } catch (FileNotFoundException e) {
      System.err.println("Error " + e);
      this.run();
    }
    System.out.println("\n");
    System.out.println("What is the word we are looking for?");
    String searchWord = myScan.nextLine();
    List<String> narrowSearch = new ArrayList<String>();
    String nextAns;
    if (header) {
      System.out.println("What is the column name to narrow the search? (Hit enter if no preference)");
      nextAns = myScan.nextLine();
      if (nextAns == "") {
        //calling the search constructor without narrowing the search
      } else {
        while (nextAns != "") {
          narrowSearch.add(nextAns);
          System.out.println("More parameters? (Hit enter if no parameter)");
          nextAns = myScan.nextLine();
        }
        //calling the search constructor with narrowing the search
      }
    } else {
      System.out.println(
          "If there are columns that would help narrow the search, please enter their"
              + " index. Note that the indexing starts from 0. (Hit enter if no column)");
      // maybe create a list of integers given these are column indices
      nextAns = myScan.nextLine();
      if (nextAns == "") {
        //call teh search constructor without narrowing the search
      } else {
        while (nextAns != "") {
          narrowSearch.add(nextAns);
          System.out.println("More columns? (Hit enter if no parameter)");
          nextAns = myScan.nextLine();
        }
        //call the search constructor with narrowing the search
      }
    }
    // call Searcher.search or print lines in some other ways and should be good to go!
  }
}
