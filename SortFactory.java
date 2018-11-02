/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Factory pattern implementation for creating an object that implements one of
 * the sorting algorithms. Users should create an instance of this class, then
 * invoke buildSorter on it with the desired algorithm name.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 * 
 */
public class SortFactory<E> {

  /**
   * Create an object that implements a sorting algorithm.
   *
   * @param algorithm
   *          The name of the algorithm to implement.
   * @return The object that sorts items.
   */
  public Sorter<E> buildSorter(String algorithm) {
    switch (algorithm) {

      case "Bubble":
      case "bubble":

        BubbleSorter<E> bubble = new BubbleSorter<>();
        return bubble;

      case "Insertion":
      case "insertion":

        InsertionSorter<E> insert = new InsertionSorter<>();
        return insert;

      case "Merge":
      case "merge":

        MergeSorter<E> merge = new MergeSorter<>();
        return merge;

      case "Quick":
      case "quick":

        QuickSorter<E> quick = new QuickSorter<>();
        return quick;

      case "Selection":
      case "selection":

        SelectionSorter<E> select = new SelectionSorter<>();
        return select;

      default:
        return null;
    }
  }
}
