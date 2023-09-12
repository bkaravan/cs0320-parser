package edu.brown.cs.student.main.parser;

import java.io.*;

/**
 * MyParser is a parser class that is responsible for going through the CSV file and creating a
 * certain database out of it. It has two constructors, one if provided a path to the file, another
 * for any type of reader object
 */
public class MyParser {

  private BufferedReader breader;
  private boolean isHeader;

  public MyParser(String filepath, boolean header) throws FileNotFoundException {
    FileReader myReader = null;
    myReader = new FileReader(filepath);
    this.breader = new BufferedReader(myReader);
    this.isHeader = header;
  }

  public MyParser(Reader obj) { //try to make this default to false for this constructor
    this.breader = new BufferedReader(obj);
  }

  public void toParse() {

  }


}
