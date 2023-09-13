package edu.brown.cs.student.main.searcher;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a searcher class that will go through the database created by parser and look for the
 * rows that have words that were identified by the user
 */

public class MySearcher {

  private ArrayList<List<String>> dataset;
  private String locate;
  private List<String> headerLine;
  private boolean isHeader;

  public MySearcher(ArrayList<List<String>> dataset, boolean header, String key) {
    this.dataset = dataset;
    this.locate = key;
    this.isHeader = header;
    this.setUp();
  }

  public MySearcher(ArrayList<List<String>> dataset, boolean header) {
    this.dataset = dataset;
    this.locate = "";
    this.isHeader = header;
    this.setUp();
  }

  private void setUp() {
    if (isHeader) {
      this.headerLine = this.dataset.get(0);
      System.out.println(this.headerLine);
    }
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
    } else {
      int index = this.headerLine.indexOf(locate);
      System.out.println(index);
      for (List<String> row : this.dataset) {
        if (row.get(index).equals(toFind)) {
          System.out.println(row.get(index));
          return row;
        }
      }
    }
    return new ArrayList<String>();
  }
}
