import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class BinTreeTest {

  // We only want to do the IO once, so cache a copy in here
  private static List<Movie> starterList;
  private static List<Movie> mediumList;
  private static List<Movie> imdbList;

  // Initialize the lists from the CSV files
  @BeforeAll
  public static void buildLists() {
    starterList = buildList("C:\\Users\\LiemK\\Downloads\\starter.csv");
    mediumList = buildList("C:\\Users\\LiemK\\Downloads\\medium.csv");
    imdbList = buildList("C:\\Users\\LiemK\\Downloads\\imdb.csv");
  }

  private static List<Movie> buildList(String file) {
    CSVParse parser = new CSVParse();
    List<Movie> movies = parser.buildMovieList(file);
    return movies;
  }

  // Perform an in-order traversal
  private class InOrder<E> implements Iterator<E> {

    private Stack<BinTree<E>> parents;
    private BinTree<E> current;
    private BinTree<E> root;
    private boolean rootVisited;

    public InOrder(BinTree<E> tree) {
      rootVisited = false;
      root = tree;
      current = tree;
      parents = new Stack<>();
      while (current.getLeft() != null) {
        parents.push(current);
        current = current.getLeft();
      }

      // Special case: root has no left, so we visit root first
      if (root.getLeft() == null) {
        rootVisited = true;
      }
    }

    public boolean hasNext() {
      return current != null;
    }

    public E next() {
      E m = current.getElement();
      // Right subtree is non-empty; get left-most
      if (current.getRight() != null) {
        parents.push(current);
        current = current.getRight();
        while (current.getLeft() != null) {
          parents.push(current);
          current = current.getLeft();
        }
      } else if (parents.size() == 0) {
        // Right subtree is empty and we are at root; done
        current = null;
      } else {
        // Right subtree is empty; move up parents
        BinTree<E> previous = current;
        //BinTree<Integer> previous = current;
        current = parents.pop();
 
        // If current->right is previous, we have been visiting right subtree
        while (parents.size() > 0 && (current.getRight() == previous)) {
          previous = current;
          current = parents.pop();
        }
        if (parents.size() == 0) {
          if (rootVisited) {
            current = null;
          } else {
            rootVisited = true;
          }
        }

      }


      return m;
    }

  }

  public void testInOrderInteger(int number) {
    List<Integer> list = new ArrayList<>();
    for (int i = 1; i <= number; i++) {
      list.add(i);
    }

    BinTreeBuilder<Integer> builder = new BinTreeBuilder<>();
    BinTree<Integer> tree = builder.build(list);

    Iterator<Integer> iter = new InOrder<>(tree);
    int count = 0;
    while (iter.hasNext()) {
      Integer it = iter.next();
      count++;
      assertEquals(count, it.intValue(), "Expected to get 1 to " + number + " in order");
    }
    assertEquals(list.size(), count, "Tree traversal had different number than full list");
  }

  public void testInOrderMovie(List<Movie> list) {
    BinTreeBuilder<Movie> builder = new BinTreeBuilder<>();
    BinTree<Movie> tree = builder.build(list);

    Iterator<Movie> iter = new InOrder<>(tree);
    int count = 0;
    while (iter.hasNext()) {
      Movie m = iter.next();
      assertEquals(list.get(count++).toString(), m.toString());
    }
    assertEquals(list.size(), count, "Tree traversal had different number than full list");
  }


  @Test
  public void testIntegerRootOnlyBinTree() {
    testInOrderInteger(1);
  }

  // Tree should be:
  //     3
  //   /   \
  //  1     4
  //   \     \
  //    2     5
  @Test
  public void testIntegerNotFullBinTree() {
    testInOrderInteger(5);
  }

  // Tree should be:
  //      3
  //    /   \
  //   1     5
  //    \   / \
  //     2 4   6
  @Test
  public void testIntegerAlmostCompleteBinTree() {
    testInOrderInteger(6);
  }

  @Test
  public void testIntegerCompleteBinTree() {
    testInOrderInteger(7);
  }

  @Test
  public void testStarterBinTree() {
    testInOrderMovie(starterList);
  }

  @Test
  public void testMediumBinTree() {
    testInOrderMovie(mediumList);
  }

  @Test
  public void testImdbBinTree() {
    testInOrderMovie(imdbList);
  }

}
