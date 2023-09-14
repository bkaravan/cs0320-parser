package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import edu.brown.cs.student.main.parser.MyParser;
import edu.brown.cs.student.main.rowHandler.RowHandler;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import org.junit.jupiter.api.Test;

public class Testing {

  @Test
  public void sampleTest() {
    assertTrue(true);
  }

  @Test
  public void createParse() {
    String example =
        """
         value1, value2, value3
         value4, value5, value6,
         value7, value8, value9
        """;
    StringReader read = new StringReader(example);
    MyParser parser = new MyParser(read, new RowHandler());
    parser.toParse();
    ArrayList<ArrayList<String>> testSet = parser.getDataset();
    ArrayList<String> row1 = new ArrayList<>(Arrays.asList("value3", " value5", " value6"));
    ArrayList<String> row2 = new ArrayList<>(Arrays.asList(" value4", " value5", " value6"));
    assertTrue(testSet.contains(row2));
    assertFalse(testSet.contains(row1));
  }
}
