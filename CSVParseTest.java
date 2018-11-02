import java.util.Iterator;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
 * This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * JUnit test suite that tests the CSVParse class.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 *
 */
class CSVParseTest {

  CSVParse parse = new CSVParse();
  List<Movie> result;

  @Test
  void buildMovieListTest() {

    result = parse.buildMovieList(
        "C:\\Users\\LiemK\\Documents\\College Work\\Semester 3\\CS 240\\workspace\\Project 2\\starter.csv");

    Iterator<Movie> it = result.iterator();
    int index = 0;
    int size = 0;
    while (it.hasNext()) {

      size++;

      if (index < size) {

        System.out.println(result.get(index).toString());

        Iterator<String> genres = result.get(index).getGenres().iterator();

        while (genres.hasNext()) {

          System.out.println(genres.next());

        }

        index++;

      }

    }

  }

}
