package edu.brown.cs.student.main.parser;

import edu.brown.cs.student.main.rowhandler.CreatorFromRow;
import edu.brown.cs.student.main.rowhandler.FactoryFailureException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.regex.Pattern;

/**
 * MyParser is a parser class that is responsible for going through the CSV file and creating a
 * certain database out of it.
 */
public class MyParser<T> {
  static final Pattern regexSplitCSVRow =
      Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
  private ArrayList<T> dataset;
  private CreatorFromRow<T> creator;
  private BufferedReader breader;

  /**
   * constructor for MyParser class. It takes in a Reader object and creates a buffered reader out
   * of it, and an instance of a class that implements the CreatorFromRow interface that uses a
   * generic type T. This type is used to create Rows and store them in the dataset, which is an
   * ArrayList of objects of type T.
   *
   * @param obj a reader object
   * @param creator an object that implements the creatorFromRow interface that is responsible for
   *                creating rows
   */
  public MyParser(Reader obj, CreatorFromRow<T> creator) {
    this.breader = new BufferedReader(obj);
    this.creator = creator;
    this.dataset = new ArrayList<T>();
  }

  /**
   * Method that uses the reader field to go through the file and parse each row using create, and
   * creates a dataset of every row.
   */
  public void toParse() {
    try {
      String line = this.breader.readLine();
      while (line != null) {
        this.dataset.add(this.creator.create(Arrays.asList(regexSplitCSVRow.split(line))));
        line = this.breader.readLine();
      }
      this.breader.close();
    } catch (IOException e) {
      System.out.println("Error " + e);
    } catch (FactoryFailureException e) {
      System.out.println("Error " + e);
    }
  }

  /**
   * A getter method to pass the parsed information into the searcher.
   *
   * @return the dataset, which is an ArrayList of T objects after parsing the file
   */
  public ArrayList<T> getDataset() {
    return this.dataset;
  }
}
