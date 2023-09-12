package edu.brown.cs.student.main;

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
    System.out.println("Welcome to the CSV parser. What is the name of your file?");
    String path = myScan.nextLine();
    System.out.println("Parsing your file...");
    // call the constructor for parser, return the database something
    System.out.println("What is the word we are looking for?");
    String searchWord = myScan.nextLine();
    System.out.println("Does you file have a header row? (Y/N)");
    boolean header;
    String headerAns = myScan.nextLine().toLowerCase();
    if (headerAns == "y") {
      header = true;
    } else {
      header = false;
    }
    List<String> narrowSearch = new ArrayList<String>();
    String nextAns;
    if (header) {
      System.out.println("What is the parameter to narrow the search? (Hit enter if no parameter)");
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
              + "index. Note that the indexing starts from 0. (Hit enter if no column)");
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
