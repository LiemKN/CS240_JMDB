import java.util.Comparator;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Class that sorts a list given a list and a comparator lambda using selection
 * sort.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class SelectionSorter<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {

    for (int i = 0; i < list.size(); i++) {

      /* Set index where min will be. */
      int min = i;

      for (int k = i + 1; k < list.size(); k++) {

        /* Compare value at min index to the other values in the list. */
        if (comp.compare(list.get(min), list.get(k)) > 0) {

          min = k;

        }

        E minValue = list.get(min);

        /* Shift everything over as necessary. */
        while (min > i) {

          list.set(min, list.get(min - 1));
          min--;

        }

        list.set(i, minValue);

      }

    }

  }

}
