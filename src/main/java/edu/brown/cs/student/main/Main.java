package edu.brown.cs.student.main;

import edu.brown.cs.student.main.parser.MyParser;
import edu.brown.cs.student.main.rowHandler.FactoryFailureException;
import edu.brown.cs.student.main.rowHandler.RowHandler;
import edu.brown.cs.student.main.searcher.MySearcher;
import java.io.*;
import java.io.FileNotFoundException;

/** The Main class of our project. This is where execution begins. */
public final class Main {
  private String[] args;

  /**
   * The initial method called when execution begins.
   *
   * @param args An array of command line arguments
   */
  public static void main(String[] args) throws IOException, FactoryFailureException {
    new Main(args).run();
  }

  private Main(String[] args) {
    this.args = args;
  }

  private void run() throws FileNotFoundException, IOException, FactoryFailureException {
    //    Scanner myScan = new Scanner(System.in);
    //    System.out.println(
    //        "Welcome to the CSV parser. Please provide the path to the file (For example, "
    //            + "data/census/income_by_race_edited.csv, if the file is in the working
    // directory)");
    //    String path = myScan.nextLine();
    //    System.out.println("Does your file have a header row? (Y/N)");
    //    String headerAns = myScan.nextLine();
    //    boolean header = headerAns.toLowerCase().equals("y");
    //    try {
    //      FileReader myReader = new FileReader(path);
    //      RowHandler row = new RowHandler();
    //      MyParser parser = new MyParser(myReader, header, row);
    //      parser.toParse();
    //    } catch (FileNotFoundException e) {
    //      System.err.println(e);
    //      this.run();
    //    } catch (IOException e) {
    //      System.err.println(e);
    //    }
    //    System.out.println("What is the word we are looking for?");
    //    String searchWord = myScan.nextLine();
    //    System.out.println("Is there a colum specifier? (Y/N) (Hit enter if none)");
    //    String specifier = myScan.nextLine();
    //    if (!specifier.isEmpty()) {
    //      if (header) {
    //        System.out.println("Please enter the name of the column to look for");
    //      } else {
    //        System.out.println("Please enter the index of the column to look for");
    //      }
    //      specifier = myScan.nextLine();
    //      //call the searcher with
    //    } else {
    //      //call the searcher without
    // args params = filepath, header, searchword, keyword
    FileReader myReader = new FileReader(args[0]);
    RowHandler rowHandler = new RowHandler();
    boolean header = args[1].equals("true");
    MyParser parser = new MyParser(new BufferedReader(myReader), rowHandler);
    parser.toParse();
    String narrow;
    if (args.length == 3) {
      narrow = "NULL";
    } else {
      narrow = args[3];
    }
    MySearcher searcher = new MySearcher(parser.getDataset(), header, narrow);
    searcher.findRows(args[2]);
  }
}
