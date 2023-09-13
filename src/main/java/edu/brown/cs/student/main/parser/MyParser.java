package edu.brown.cs.student.main.parser;

import edu.brown.cs.student.main.rowHandler.CreatorFromRow;
import edu.brown.cs.student.main.rowHandler.FactoryFailureException;
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

  private ArrayList<List<String>> dataset;
  private CreatorFromRow<T> creator;
  private BufferedReader breader;
  private boolean isHeader;


  public MyParser(Reader obj, boolean header, CreatorFromRow<T> creator) {
    this.breader = new BufferedReader(obj);
    this.isHeader = header;
    this.creator = creator;
//    this.toParse();
  }

  private void toParse() throws IOException, FactoryFailureException {
    if (this.isHeader) {
      String header = this.breader.readLine();
      System.out.println(List.of(regexSplitCSVRow.split(header)));
    }
    try {
      String line = this.breader.readLine();
      while (line != null) {
        String[] res = regexSplitCSVRow.split(line);
//        this.creator.create();
      }
    } catch (IOException e) {
      System.out.println("Error " + e);
//    } catch (FactoryFailureException e) {
//
//    }
  }


}
