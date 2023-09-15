package edu.brown.cs.student.main.searcher;

import static java.lang.System.exit;

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
    if (this.isHeader) {
      this.startIndex = 1;
    } else {
      this.startIndex = 0;
    }
    String match = this.narrow.substring(0, 4).toLowerCase();
    switch (match) {
      case "ind:":
        try {
          this.narrowIndex = Integer.parseInt(this.narrow.substring(4).strip());
          if (this.narrowIndex >= this.dataset.get(0).size()) {
            System.err.println("Please make sure that you provide a valid Index");
            System.exit(0);
          }
        } catch (NumberFormatException e) {
          System.err.println("Please make sure to use an integer after Ind: ");
          System.exit(0);
        }
        break;
      case "nam:":
        if (this.isHeader) {
          this.narrowIndex = this.dataset.get(0).indexOf(this.narrow.substring(4).strip());
        } else {
          System.err.println("Please only search by column name when the header row is present");
          System.exit(0);
        }
        break;
      default:
        this.narrowIndex = -1;
    }
  }

  private void indexSearch(String toFind) {
    for (int i = this.startIndex; i < this.dataset.size(); i++) {
      List<String> row = this.dataset.get(i);
      if (row.get(this.narrowIndex).equals(toFind)) {
        this.found.add(row);
      }
    }
  }

  private void allSearch(String toFind) {
    for (int i = this.startIndex; i < this.dataset.size(); i++) {
      List<String> row = this.dataset.get(i);
      for (String ele : row) {
        if (ele.equals(toFind)) {
          this.found.add(row);
        }
      }
    }
  }


  public void findRows(String toFind) {
    if (this.narrowIndex == -1) {
      this.allSearch(toFind);
    } else {
      this.indexSearch(toFind);
    }
  }

  public ArrayList<List<String>> getFound() {
    return this.found;
  }
}
