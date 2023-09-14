package edu.brown.cs.student.main.parser;

import edu.brown.cs.student.main.rowHandler.CreatorFromRow;
import edu.brown.cs.student.main.rowHandler.FactoryFailureException;
import java.util.Arrays;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import java.util.regex.Pattern;

/**
 * MyParser is a parser class that is responsible for going through the CSV file and creating a
 * certain database out of it. It has two constructors, one if provided a path to the file, another
 * for any type of reader object
 */
public class MyParser<T> {
  static final Pattern regexSplitCSVRow = Pattern.compile(",(?=([^\\\"]*\\\"[^\\\"]*\\\")*(?![^\\\"]*\\\"))");
  private ArrayList<T> dataset;
  private CreatorFromRow<T> creator;
  private BufferedReader breader;


  public MyParser(Reader obj, CreatorFromRow<T> creator) {
    this.breader = new BufferedReader(obj);
    this.creator = creator;
  }

  public void toParse() {
    this.dataset = new ArrayList<T>();
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

  public ArrayList<T> getDataset() {
    return this.dataset;
  }

}
