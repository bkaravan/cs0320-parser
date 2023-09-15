package edu.brown.cs.student.main.parser;

import edu.brown.cs.student.main.rowHandler.FactoryFailureException;
import java.util.List;

public class ParsedRow {
  public int index;
  public List<String> contents;

  public ParsedRow(int num, List<String> contents) {
    this.index = num;
    this.contents = contents;
  }

  public String toString() {
    String retStr = "";
    retStr += this.index;
    for (String ele : this.contents) {
      retStr = retStr + ", " + ele;
    }
    return retStr;
  }

}
