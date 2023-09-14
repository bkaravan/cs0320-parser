package edu.brown.cs.student.main.rowHandler;

import java.util.ArrayList;
import java.util.List;

public class RowHandler implements CreatorFromRow<ArrayList<String>> {

  public RowHandler() {}

  public ArrayList<String> create(List<String> row) throws FactoryFailureException {
    ArrayList<String> newRow = new ArrayList<>();
    for (String str : row) {
      newRow.add(str.strip());
    }
    return newRow;
  }
}
