package edu.brown.cs.student.main.rowHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * RowHandler is a class that is passed in MyParser in order to work with this particular
 * implementation of MySearcher. It implements the CreatorFromRow array using ArrayList of Stirngs,
 * so the dataset in MyParser becomes a list of list of string.
 */

public class RowHandler implements CreatorFromRow<ArrayList<String>> {

  /**
   * create class take in a list of strings, strips every string of spaces, and returns an ArrayList
   * of the input List of Strings
   * @param row
   * @return an ArrayList of stings from the input that is stripped of spaces
   * @throws FactoryFailureException
   */

  public ArrayList<String> create(List<String> row) throws FactoryFailureException {
    ArrayList<String> newRow = new ArrayList<>();
    for (String str : row) {
      newRow.add(str.strip());
    }
    return newRow;
  }
}
