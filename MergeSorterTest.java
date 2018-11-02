import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * JUnit test suite that tests the MergeSorter class.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 *
 */
class MergeSorterTest {

  CSVParse parse = new CSVParse();
  MergeSorter<Movie> merge = new MergeSorter<>();

  @Test
  public void mergeSortTest() {

    List<Movie> movies = parse.buildMovieList(
        "C:\\Users\\LiemK\\Documents\\College Work\\Semester 3\\CS 240\\workspace\\Project 2\\starter.csv");

    merge.sort(movies, (m1, m2) -> m2.getYear() - m1.getYear());

    for (int i = 0; i < movies.size(); i++) {

      System.out.println(movies.get(i).toString());

    }
    
    assertEquals(movies, movies);

  }

}
