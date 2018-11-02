import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/*
 * This work complies with the JMU Honor Code.
* References and Acknowledgments: I collaborated on a solution with Matthew Routon.
*/

/**
 * Parser for creating a list of movies from a comma-separated value (CSV) file.
 * The allowable syntax of CSV files can be somewhat complex. Our simplified
 * format allows anything to be part of any data field, except for quotation
 * marks ("). Quotation marks are reserved for grouping data fields that
 * themselves contain a comma. For instance, consider the following line:
 *
 * <p>hello,"Peter, Paul, and Mary",how,are,you
 *
 * <p>This line contains 5 data fields, where the second is the string "Peter,
 * Paul, and Mary". Note that your String object should not contain the
 * quotation marks.
 * 
 * <p>Another simplifying factor is that every line has exactly one data field per
 * member of the Movie class. You do not need to handle ill-formed data files.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class CSVParse {

  /**
   * Main interface for parsing a CSV file into a movie list.
   *
   * @param csvFile
   *          The name of the file to parse.
   * @return The list of movies in the same order as the file contents.
   * @throws IOException
   *           exception thrown.
   */
  public static List<Movie> buildMovieList(String csvFile) {

    String readLine = "";
    List<Movie> result = new ArrayList<>();
    BufferedReader buffReader;
    try {

      buffReader = new BufferedReader(new FileReader(csvFile));

      try {

        while ((readLine = buffReader.readLine()) != null) {

          /*
           * Parse the csv file in new line characters.
           */
          String[] movieInfoInLines = readLine.split("\n");

          for (int i = 0; i < movieInfoInLines.length; i++) {

            /*
             * Parse by comma then quote.
             */
            String[] splitByCommaQuote = movieInfoInLines[i].split(",\"");

            /*
             * Take care of the genre section.
             */
            splitByCommaQuote[2] = splitByCommaQuote[2].substring(0,
                splitByCommaQuote[2].length() - 1);
            String[] genre = splitByCommaQuote[2].split(",");

            List<String> genres = new ArrayList<>();
            for (int j = 0; j < genre.length; j++) {

              genres.add(genre[j]);

            }

            /*
             * Extract middle section containing title, rating, runtime, and year. The split
             * to separate the title from the numbers.
             */
            String titleAndNumbers = splitByCommaQuote[1];
            String[] splitTitleAndNumbers = titleAndNumbers.split("\",");

            /*
             * Extract the numbers and split them.
             */
            String numbers = splitTitleAndNumbers[1];
            String[] splitNumbers = numbers.split(",");

            /*
             * Make a new movie with the parsed information.
             */
            Movie add = new Movie(splitByCommaQuote[0], splitTitleAndNumbers[0],
                Double.parseDouble(splitNumbers[0]), Integer.parseInt(splitNumbers[1]),
                Integer.parseInt(splitNumbers[2]), genres);

            result.add(add);

          }

        }

      } catch (NumberFormatException e1) {

        e1.printStackTrace();

      } catch (IOException e1) {

        e1.printStackTrace();

      }

      try {

        buffReader.close();

      } catch (IOException e) {

        e.printStackTrace();

      }

    } catch (FileNotFoundException e) {

      e.printStackTrace();

    }

    return result;

  }

}
