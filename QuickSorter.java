import java.util.Comparator;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I used https://www.tutorialspoint.com/data_structures_algorithms/quick_sort_algorithm.htm as inspiration to make this method.
*/

/**
 * Class that sorts a list given a list and a comparator lambda using quicksort.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 *
 */
public class QuickSorter<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {

    quicksort(list, comp, 0, list.size() - 1);

  }

  private void quicksort(List<E> list, Comparator<E> comp, int begin, int end) {

    if (begin >= end) {

      return;

    }

    /* Split up the list. */
    E pivot = list.get((begin + end) / 2);
    int index = partition(list, comp, begin, end, pivot);
    quicksort(list, comp, begin, index - 1);
    quicksort(list, comp, index, end);

  }

  private int partition(List<E> list, Comparator<E> comp, int begin, int end, E pivot) {

    while (begin <= end) {

      /* Find a value to the left of the pivot that's greater than the pivot. */
      while (comp.compare(pivot, list.get(begin)) > 0) {

        begin++;

      }

      /* Find a value to the right of the pivot that's less than the pivot. */
      while (comp.compare(list.get(end), pivot) > 0) {

        end--;

      }

      if (begin <= end) {

        /* Swaps the values at the found indexes. */
        E temp = list.get(begin);
        list.set(begin, list.get(end));
        list.set(end, temp);
        begin++;
        end--;

      }

    }

    return begin;

  }

}
