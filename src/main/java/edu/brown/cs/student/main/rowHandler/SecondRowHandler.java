package edu.brown.cs.student.main.rowHandler;

import edu.brown.cs.student.main.parser.ParsedRow;
import java.util.HashMap;
import java.util.List;

public class SecondRowHandler implements CreatorFromRow<ParsedRow> {
  private int count;

  public SecondRowHandler() {
    this.count = 0;
  }
  public ParsedRow create(List<String> row) throws FactoryFailureException{
    ParsedRow retRow = new ParsedRow(this.count, row);
    this.count++;
    return retRow;
  }
}
