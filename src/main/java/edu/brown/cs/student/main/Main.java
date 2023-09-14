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

  /**
   * These specs are repeated in the README and are vital for program to run smoothly:
   * args[0] - string filepath
   * args[1] - string searchWord
   * args[2] - boolean for the header ("True"/"False")
   * args[3] - string for narrowing the search, that specifies either the name search or index search
   * example: Ind: 0; Nam: Position
   * @param args
   */
  private Main(String[] args) {
    this.args = args;
  }

  /**
   * the method that runs Parser on the filepath and searcher on the dataset from the parser
   * @throws FileNotFoundException
   * @throws IOException
   * @throws FactoryFailureException
   */
  private void run() throws FileNotFoundException, IOException, FactoryFailureException {
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
