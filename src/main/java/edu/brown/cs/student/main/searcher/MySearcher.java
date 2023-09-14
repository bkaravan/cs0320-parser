package edu.brown.cs.student.main.searcher;

import java.util.ArrayList;
import java.util.List;

/**
 * This is a searcher class that will go through the database created by parser and look for the
 * rows that have words that were identified by the user
 */
public class MySearcher {

  private ArrayList<List<String>> found;

  private ArrayList<List<String>> dataset;
  private int narrowIndex;
  private String narrow;
  private boolean isHeader;
  private int startIndex;

  public MySearcher(ArrayList<List<String>> dataset, boolean header, String key) {
    this.dataset = dataset;
    this.narrow = key;
    this.isHeader = header;
    this.found = new ArrayList<>();
    this.setUp();
  }

  // add throwing if trying to parse not int
  private void setUp() {
    if (isHeader) {
      this.startIndex = 1;
      this.narrowIndex = this.dataset.get(0).indexOf(this.narrow);
    } else {
      this.startIndex = 0;
      if (!narrow.equals("NULL")) {
        this.narrowIndex = Integer.parseInt(this.narrow);
      } else {
        this.narrowIndex = -1;
      }
    }
  }

  public void findRows(String toFind) {
    if (this.narrowIndex == -1) {
      for (int i = this.startIndex; i < this.dataset.size(); i++) {
        List<String> row = this.dataset.get(i);
        for (String ele : row) {
          if (ele.equals(toFind)) {
            this.found.add(row);
          }
        }
      }
    } else {
      for (int i = this.startIndex; i < this.dataset.size(); i++) {
        List<String> row = this.dataset.get(i);
        if (row.get(this.narrowIndex).equals(toFind)) {
          this.found.add(row);
        }
      }
    }
  }

  public ArrayList<List<String>> getFound() {
    return this.found;
  }
}
