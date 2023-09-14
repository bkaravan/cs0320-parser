package edu.brown.cs.student.main.searcher;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a searcher class that will go through the database created by parser and look for the
 * rows that have words that were identified by the user
 */
public class MySearcher {

  private ArrayList<List<String>> dataset;
  private int narrowIndex;
  private String narrow;
  private boolean isHeader;

  public MySearcher(ArrayList<List<String>> dataset, boolean header, String key) {
    this.dataset = dataset;
    this.narrow = key;
    this.isHeader = header;
    this.setUp();
  }

  private void setUp() {
    if (isHeader) {
      if (!narrow.isEmpty()) {
        this.narrowIndex = this.dataset.get(0).indexOf(narrow);
      }
    } else {
      if (!narrow.isEmpty()) {
        this.narrowIndex = Integer.parseInt(narrow);
      } else {
        this.narrowIndex = -1;
      }
    }
  }

  public void findRow(String toFind) {
    if (this.narrowIndex == -1) {
      for (List<String> row : this.dataset) {
        for (String ele : row) {
          if (ele.equals(toFind)) {
            System.out.println(row);
          }
        }
      }
    } else {
      for (List<String> row : this.dataset) {
        if (row.get(this.narrowIndex).equals(toFind)) {
          System.out.println(row);
        }
      }
    }
  }
}
