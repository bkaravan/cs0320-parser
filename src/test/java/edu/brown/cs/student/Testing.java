package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

import edu.brown.cs.student.main.parser.MyParser;
import edu.brown.cs.student.main.rowHandler.RowHandler;
import edu.brown.cs.student.main.searcher.MySearcher;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Testing {
  private ArrayList<ArrayList<String>> testSet;
  private RowHandler creator = new RowHandler();
  private MySearcher searcher;
  private MyParser parser;

  @Test
  public void parseStringReader() {
    String example =
        """
             value1, value2, value3
             value4, value5, value6,
             value7, value8, value9
            """;
    StringReader read = new StringReader(example);
    this.parser = new MyParser(read, this.creator);
    this.parser.toParse();
    this.testSet = parser.getDataset();
    assertEquals(3, this.testSet.size());
  }

  @Test
  public void parseCheckDataset() {
    String example =
        """
             value1, value2, value3
             value4, value5, value6,
             value7, value8, value9
            """;
    StringReader read = new StringReader(example);
    this.parser = new MyParser(read, this.creator);
    this.parser.toParse();
    this.testSet = parser.getDataset();
    ArrayList<String> row1 = new ArrayList<>(Arrays.asList("value3", "value5", "value6"));
    ArrayList<String> row2 = new ArrayList<>(Arrays.asList("value4", "value5", "value6"));
    assertTrue(this.testSet.contains(row2));
    assertFalse(this.testSet.contains(row1));
  }

  @Test
  public void parseFileReader() throws FileNotFoundException {
    String filepath = "data/stars/ten-star.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.testSet = parser.getDataset();
    assertEquals(11, this.testSet.size());
    ArrayList<String> row1 =
        new ArrayList<>(Arrays.asList("3759", "96 G. Psc", "7.26388", "1.55643", "0.68697"));
    ArrayList<String> row2 =
        new ArrayList<>(Arrays.asList("96 G. Psc", "7.26388", "1.55643", "0.68697"));
    assertTrue(this.testSet.contains(row1));
    assertFalse(this.testSet.contains(row2));
  }

  @Test
  public void fileNotFoundTest() {
    String filepath = "Not a File!";
    assertThrows(
        FileNotFoundException.class, () -> new MyParser(new FileReader(filepath), this.creator));
  }

  // add a test that throws factury failure
  // add a test that makes rowhandler a different object than arrayList string

  // found noH noN
  @Test
  public void searchFoundNoNarrowNoHeader() {
    String example =
        """
             value1, value2, value3
             value4, value5, value6,
             value7, value8, value9
            """;
    StringReader read = new StringReader(example);
    this.parser = new MyParser(read, this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), false, "NULL");
    this.searcher.findRows("value5");
    ArrayList<String> compare = new ArrayList<>(List.of("value4", "value5", "value6"));
    ArrayList<String> res = new ArrayList<>(this.searcher.getFound().get(0));
    assertEquals(res, compare);
    assertEquals(1, this.searcher.getFound().size());
  }

  // not found noH noN
  @Test
  public void searchNotFoundNoNarrowNoHeader() {
    String example =
        """
             value1, value2, value3
             value4, value5, value6,
             value7, value8, value9
            """;
    StringReader read = new StringReader(example);
    this.parser = new MyParser(read, this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), false, "NULL");
    this.searcher.findRows("supervalue");
    assertEquals(0, this.searcher.getFound().size());
  }

  // found noH N
  @Test
  public void searchFoundNarrowNoHeader() {
    String example =
        """
             value1, value2, value3
             value4, value5, value6,
             value7, value8, value9
            """;
    StringReader read = new StringReader(example);
    this.parser = new MyParser(read, this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), false, "2");
    this.searcher.findRows("value9");
    ArrayList<String> compare = new ArrayList<>(List.of("value7", "value8", "value9"));
    assertEquals(1, this.searcher.getFound().size());
    assertEquals(compare, this.searcher.getFound().get(0));
  }

  // found H noN
  @Test
  public void searchFoundNoNarrowHeader() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "NULL");
    this.searcher.findRows("right");
    assertEquals(this.searcher.getFound().size(), 2);
  }
  // found H N

  @Test
  public void searchFoundNarrowHeader() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "class");
    this.searcher.findRows("second");
    ArrayList<String> compare = new ArrayList<>(List.of("bohdan", "second", "left"));
    ArrayList<String> compare0 = new ArrayList<>(List.of("jake", "second", "right"));
    assertEquals(this.searcher.getFound().size(), 2);
    assertEquals(this.searcher.getFound().get(1), compare);
    assertEquals(this.searcher.getFound().get(0), compare0);
  }

  // the word is in the dataset but not in the desired column
  @Test
  public void searchNotFoundWrongNarrow() throws FileNotFoundException{
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "class");
    this.searcher.findRows("bohdan");
    assertEquals(this.searcher.getFound().size(), 0);
  }
}
