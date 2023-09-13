package edu.brown.cs.student.main.searcher;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a searcher class that will go through the database created by parser and look for the rows
 * that have words that were identified by the user
 */

public class MySearcher {
  private List<List<String>> dataset;
  private String locate;
  public MySearcher(List<List<String>> dataset, String key) {
    this.dataset = dataset;
    this.locate = key;
  }
  public MySearcher(List<List<String>> dataset) {
    this.dataset = dataset;
    this.locate = "";
  }

  public List<String> findRow(String toFind) {
    if (locate.isEmpty()) {
      for (List<String> row : this.dataset) {
        for (String ele : row) {
          if (ele.equals(toFind)) {
            return row;
          }
        }
      }
    } return new ArrayList<String>();
  }

}
