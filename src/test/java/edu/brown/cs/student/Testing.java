package edu.brown.cs.student;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.testng.Assert.assertThrows;
import static org.testng.AssertJUnit.assertEquals;

import edu.brown.cs.student.main.parser.MyParser;
import edu.brown.cs.student.main.parser.ParsedRow;
import edu.brown.cs.student.main.rowHandler.FactoryFailureException;
import edu.brown.cs.student.main.rowHandler.RowHandler;
import edu.brown.cs.student.main.rowHandler.SecondRowHandler;
import edu.brown.cs.student.main.searcher.MySearcher;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

/**
 * testing suit for this project
 */
public class Testing {

  private ArrayList<ArrayList<String>> testSet;
  private RowHandler creator = new RowHandler();
  private MySearcher searcher;
  private MyParser parser;

  /**
   * a simple test that creates a dataset out of a stringReader
   */
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

  /**
   * test that checks that contents of the dataset after parser is done running are correct
   */
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

  /**
   * test that parses a given file from a filepath and checks that it contains values from the csv
   * file
   *
   * @throws FileNotFoundException
   */
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

  /**
   * a test that checks if the parser can handle the files of bigger size
   *
   * @throws FileNotFoundException
   */
  @Test
  public void parseBigFile() throws FileNotFoundException {
    String filepath = "data/census/income_by_race_edited.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.testSet = parser.getDataset();
    assertEquals(324, this.testSet.size());
  }

  /**
   * a test case to check the parser with another implementation of CreaterFromRow interface
   *
   * @throws FactoryFailureException
   * @throws FileNotFoundException
   */
  @Test
  public void parseDifferentGeneric() throws FactoryFailureException, FileNotFoundException {
    String filepath = "data/csvtest/noHeaderTest.csv";
    this.parser = new MyParser(new FileReader(filepath), new SecondRowHandler());
    this.parser.toParse();
    ArrayList<ParsedRow> testSet1 = parser.getDataset();
    assertEquals(3, testSet1.size());
    assertEquals("1, Joe, MetCalf, 330", testSet1.get(1).toString());
  }

  /**
   * an assertThrow test to make sure that an error will be thrown when the user might use an
   * incorrect filepath
   */
  @Test
  public void fileNotFoundTest() {
    String filepath = "Not a File!";
    assertThrows(
        FileNotFoundException.class, () -> new MyParser(new FileReader(filepath), this.creator));
  }

  /**
   * search test that correctly finds a value from the string reader without header or index specs
   */
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

  /**
   * searcher test that correctly doesn't find the search word that is not present
   */
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

  /**
   * a searcher test to check that we are allowed to check by indices without a header
   */
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
    this.searcher = new MySearcher(parser.getDataset(), false, "Ind: 2");
    this.searcher.findRows("value9");
    ArrayList<String> compare = new ArrayList<>(List.of("value7", "value8", "value9"));
    assertEquals(1, this.searcher.getFound().size());
    assertEquals(compare, this.searcher.getFound().get(0));
  }

  /**
   * searcher test that correctly finds the search word when there is a header and no index specs
   *
   * @throws FileNotFoundException
   */
  @Test
  public void searchFoundNoNarrowHeader() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "NULL");
    this.searcher.findRows("right");
    assertEquals(this.searcher.getFound().size(), 2);
  }

  /**
   * Test that checks that if the search word repeats in the header column name, it only gets found
   * in the "body" of the dataset
   *
   * @throws FileNotFoundException
   */
  @Test
  public void searchHeaderDuplicate() throws FileNotFoundException {
    String filepath = "data/csvtest/duplicate.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "NULL");
    this.searcher.findRows("Country");
    assertEquals(1, this.searcher.getFound().size());
    assertTrue(this.searcher.getFound().contains(List.of("Kozelets", "Chernihiv", "Country")));
  }

  /**
   * test that correctly finds a search word given a header and a name index specifies
   *
   * @throws FileNotFoundException
   */
  @Test
  public void searchFoundNarrowHeader() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "NAM: class");
    this.searcher.findRows("second");
    ArrayList<String> compare = new ArrayList<>(List.of("bohdan", "second", "left"));
    ArrayList<String> compare0 = new ArrayList<>(List.of("jake", "second", "right"));
    assertEquals(this.searcher.getFound().size(), 2);
    assertEquals(this.searcher.getFound().get(1), compare);
    assertEquals(this.searcher.getFound().get(0), compare0);
  }

  /**
   * test that checks that if we specify the name of the column, and the search word is in the
   * dataset but is not in that column, it DOES NOT get found
   *
   * @throws FileNotFoundException
   */
  @Test
  public void searchNotFoundWrongNarrow() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "nam: class");
    this.searcher.findRows("bohdan");
    assertEquals(this.searcher.getFound().size(), 0);
  }

  /**
   * test to see that searcher can look up by index column when the header row is present
   *
   * @throws FileNotFoundException
   */
  @Test
  public void searchFoundIndexWithHeader() throws FileNotFoundException {
    String filepath = "data/stars/stardata.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "ind: 1");
    this.searcher.findRows("Cael");
    ArrayList<String> compare =
        new ArrayList<>(List.of("11", "Cael", "159.15237", "0.1036", "170.31215"));
    assertEquals(this.searcher.getFound().size(), 1);
    assertEquals(this.searcher.getFound().get(0), compare);
  }
  /**
   * test to see that if there was a mistake in providing the name of the column, the searcher
   * defaults to looking through the whole dataset and can still find the row
   */
  @Test
  public void searchWithMistake() throws FileNotFoundException {
    String filepath = "data/csvtest/test.csv";
    this.parser = new MyParser(new FileReader(filepath), this.creator);
    this.parser.toParse();
    this.searcher = new MySearcher(parser.getDataset(), true, "nam: not there!");
    this.searcher.findRows("right");
    assertEquals(2, this.searcher.getFound().size());
  }
}
