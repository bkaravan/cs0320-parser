package edu.brown.cs.student.main.parser;

import java.io.*;

/**
 * MyParser is a parser class that is responsible for going through the CSV file and creating a
 * certain database out of it. It has two constructors, one if provided a path to the file, another
 * for any type of reader object
 */
public class MyParser {

  private BufferedReader breader;

  public MyParser(String filepath) throws FileNotFoundException {
    FileReader myReader = null;
    myReader = new FileReader(filepath);
    this.breader = new BufferedReader(myReader);
  }

  public MyParser(Reader obj) {
    this.breader = new BufferedReader(obj);
  }


}
