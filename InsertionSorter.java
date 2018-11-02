import java.util.Comparator;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Class that sorts a list given a list and a comparator lambda using insertion
 * sort.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 *
 */
public class InsertionSorter<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {

    for (int i = 1; i < list.size(); i++) {

      /* Declare a comparison value and index for later. */
      E compareWith = list.get(i);
      int other = i - 1;

      /* Go through the other values in the list and compare. */
      while (other >= 0 && comp.compare(list.get(other), compareWith) > 0) {

        list.set(other + 1, list.get(other));
        other--;

      }

      list.set(other + 1, compareWith);

    }

  }

}
