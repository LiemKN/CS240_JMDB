import java.util.Comparator;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * BubbleSorter class that sorts a list given a list and comparator lambda.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class BubbleSorter<E> implements Sorter<E> {

  /**
   * Sort a list based by bubble sort. If the function returns a negative value,
   * then the first item is less than the second and should appear earlier in the
   * list. A positive value indicates a larger item. Returning 0 indicates they
   * match.
   *
   * @param list
   *          The list of items to sort.
   * @param comp
   *          The function for comparing two instances of E.
   */
  @Override
  public void sort(List<E> list, Comparator<E> comp) {

    for (int i = 0; i < list.size(); i++) {

      for (int k = 0; k < list.size() - i - 1; k++) {

        if (comp.compare(list.get(k), list.get(k + 1)) > 0) {

          E temp = list.get(k + 1);
          list.set(k + 1, list.get(k));
          list.set(k, temp);

        }

      }

    }

  }

}
