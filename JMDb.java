import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.function.Function;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Runner class for the JMU Movie Database (JMDb).
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class JMDb {

  /**
   * The main entry point to the program. The first argument is the name of the
   * CSV file to parse. The second argument is which sorting algorithm to use.
   *
   * @param args
   *          The command-line arguments.
   */
  public static void main(String[] args) {

    /* Declaring and instantiating all necessary variables. */
    CSVParse parser = new CSVParse();
    SortFactory<Movie> sortMethod = new SortFactory<>();
    BinTreeBuilder<Movie> builder = new BinTreeBuilder<>();
    List<Movie> ratingMatch = new ArrayList<>();
    List<Movie> runtimeMatch = new ArrayList<>();
    List<Movie> yearMatch = new ArrayList<>();
    List<Movie> genreMatch = new ArrayList<>();
    List<Movie> shortestList = new ArrayList<>();
    List<Movie> finalResults = new ArrayList<>();
    boolean ratingFlag = false;
    boolean runtimeFlag = false;
    boolean yearFlag = false;
    boolean genreFlag = false;
    int minSize = Integer.MAX_VALUE;

    List<Movie> movies = parser.buildMovieList(args[0]);
    Sorter<Movie> sorter = sortMethod.buildSorter(args[1]);

    String[] brokenDownQuery = args[2].split(" ");

    /*
     * Go through the split query and retrieve the list(s) that match the criteria.
     */
    for (int i = 0; i < brokenDownQuery.length; i += 4) {

      int index = i;

      switch (brokenDownQuery[i]) {

        case "rating":

          /* Create the sorting comparator lambda. */
          Comparator<Movie> comp = (m1, m2) -> {

            if (m1.getImdbRating() - m2.getImdbRating() > 0) {

              return 1;

            } else if (m1.getImdbRating() - m2.getImdbRating() < 0) {

              return -1;

            } else {

              return 0;

            }

          };

          sorter.sort(movies, comp);
          BinTree<Movie> ratingTree = builder.build(movies);

          ratingFlag = true;

          /* Create the lambda. */
          Function<Movie, Integer> rating = (m) -> {

            if (m.getImdbRating() < Double.parseDouble(brokenDownQuery[index + 1])) {

              return -1;

            } else if (m.getImdbRating() > Double.parseDouble(brokenDownQuery[index + 2])) {

              return 1;

            } else {

              return 0;

            }

          };

          /* Use the lambda to search the movie list. */
          ratingMatch = ratingTree.search(rating);

          if (ratingMatch.size() < minSize) {

            minSize = ratingMatch.size();
            shortestList = ratingMatch;

          }

          break;

        case "runtime":

          sorter.sort(movies, (m1, m2) -> (m1.getRuntime() - m2.getRuntime()));
          BinTree<Movie> runtimeTree = builder.build(movies);
          runtimeFlag = true;

          /* Create the lambda. */
          Function<Movie, Integer> runtime = (m) -> {

            if (m.getRuntime() < Double.parseDouble(brokenDownQuery[index + 1])) {

              return -1;

            } else if (m.getRuntime() > Double.parseDouble(brokenDownQuery[index + 2])) {

              return 1;

            } else {

              return 0;

            }

          };

          /* Use the lambda to search the movie list. */
          runtimeMatch = runtimeTree.search(runtime);

          if (runtimeMatch.size() < minSize) {

            minSize = runtimeMatch.size();
            shortestList = runtimeMatch;

          }

          break;

        case "year":

          sorter.sort(movies, (m1, m2) -> (m1.getYear() - m2.getYear()));
          BinTree<Movie> yearTree = builder.build(movies);
          yearFlag = true;

          /* Create the lambda. */
          Function<Movie, Integer> year = (m) -> {

            if (m.getYear() < Double.parseDouble(brokenDownQuery[index + 1])) {

              return -1;

            } else if (m.getYear() > Double.parseDouble(brokenDownQuery[index + 2])) {

              return 1;

            } else {

              return 0;

            }

          };

          /* Use the lambda to search the movie list. */
          yearMatch = yearTree.search(year);

          if (yearMatch.size() < minSize) {

            minSize = yearMatch.size();
            shortestList = yearMatch;

          }

          break;

        case "genre":

          genreFlag = true;
          String[] genresToFind = new String[brokenDownQuery.length - 1 - index];
          int genresIndex = 0;

          /* Extract all genres to be found. */
          for (int p = index + 1; p < brokenDownQuery.length; p++) {

            genresToFind[genresIndex] = brokenDownQuery[p];
            genresIndex++;

          }

          /*
           * For each movie in the list, determine whether or not it falls into the query
           * specified genre.
           */
          for (int q = 0; q < movies.size(); q++) {

            List<String> movieGenres = movies.get(q).getGenres();

            for (int w = 0; w < genresToFind.length; w++) {

              for (int e = 0; e < movieGenres.size(); e++) {

                if (genresToFind[w].equals(movieGenres.get(e))) {

                  genreMatch.add(movies.get(q));

                }

              }

            }

          }

          break;

        default:

          break;

      }

    }

    List<Movie> origList = shortestList;

    /*
     * If a list of movies with qualifying ratings was made, compare it with the
     * shortest list.
     */
    if (ratingFlag && !origList.equals(ratingMatch)) {

      for (int i = 0; i < shortestList.size(); i++) {

        if (ratingMatch.contains(shortestList.get(i))) {

          finalResults.add(shortestList.get(i));

        }

      }

      /* Update the shortest list to the shared movies just found above. */
      shortestList = new ArrayList<>();
      for (int i = 0; i < finalResults.size(); i++) {

        shortestList.add(finalResults.get(i));

      }

    }

    /*
     * If a list of movies with qualifying runtimes was made, compare it with the
     * shortest list.
     */
    if (runtimeFlag && !origList.equals(runtimeMatch)) {

      for (int i = 0; i < shortestList.size(); i++) {

        if (runtimeMatch.contains(shortestList.get(i))) {

          finalResults.add(shortestList.get(i));

        }

      }

      /* Update the shortest list to the shared movies just found above. */
      shortestList = new ArrayList<>();
      for (int i = 0; i < finalResults.size(); i++) {

        shortestList.add(finalResults.get(i));

      }

    }

    /*
     * If a list of movies with qualifying years was made, compare it with the
     * shortest list.
     */
    if (yearFlag && !origList.equals(yearMatch)) {

      for (int i = 0; i < shortestList.size(); i++) {

        if (yearMatch.contains(shortestList.get(i))) {

          finalResults.add(shortestList.get(i));

        }

      }

      /* Update the shortest list to the shared movies just found above. */
      shortestList = new ArrayList<>();
      for (int i = 0; i < finalResults.size(); i++) {

        shortestList.add(finalResults.get(i));

      }

    }

    /*
     * If a list of movies with qualifying genres was made, compare it with the
     * shortest list.
     */
    if (genreFlag && !origList.equals(genreMatch)) {

      for (int i = 0; i < shortestList.size(); i++) {

        if (genreMatch.contains(shortestList.get(i))) {

          finalResults.add(shortestList.get(i));

        }

      }

      /* Update the shortest list to the shared movies just found above. */
      shortestList = new ArrayList<>();
      for (int i = 0; i < finalResults.size(); i++) {

        shortestList.add(finalResults.get(i));

      }

    }

    /* Print the final list. */
    finalResults = shortestList;

    for (int k = 0; k < finalResults.size(); k++) {

      System.out.print((finalResults.get(k).toString()) + "\n");

    }

  }

}
