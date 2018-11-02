import java.util.Comparator;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I used http://www.algorithmist.com/index.php/Merge_sort as inspiration to make this method.
*/

/**
 * Class that sorts a list given a list and a comparator lambda using mergesort.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class MergeSorter<E> implements Sorter<E> {

  @Override
  public void sort(List<E> list, Comparator<E> comp) {

    splitThenSort(list, comp, 0, list.size() - 1);

  }

  private void splitThenSort(List<E> list, Comparator<E> comp, int beginning, int end) {

    /* Handles the splitting of the given list. */
    if (beginning < end) {

      int mid = beginning + (end - beginning) / 2;

      /* Recursively call splitting and merge them back together at the end. */
      splitThenSort(list, comp, beginning, mid);
      splitThenSort(list, comp, mid + 1, end);
      mergeSubArrays(list, comp, beginning, mid, end);

    }

  }

  private void mergeSubArrays(List<E> list, Comparator<E> comp, int beginning, int mid, int end) {

    /* Declaring variables to be used for comparison later. */
    @SuppressWarnings("unchecked")
    E[] temp = (E[]) new Object[list.size()];
    int lower = beginning;
    int middlePlusOne = mid + 1;
    int listIndex = beginning;

    /* Fill a temporary array. */
    for (int i = beginning; i <= end; i++) {

      temp[i] = list.get(i);

    }

    /*
     * Compare each element of one subarray with the element(s) of the other, even
     * though they are sitting in the same array variable.
     */
    while (lower <= mid && middlePlusOne <= end) {

      if (comp.compare(temp[middlePlusOne], temp[lower]) >= 0) {

        list.set(listIndex, temp[lower]);
        lower++;

      } else {

        list.set(listIndex, temp[middlePlusOne]);
        middlePlusOne++;

      }
      listIndex++;

    }

    while (lower <= mid) {

      list.set(listIndex, temp[lower]);
      listIndex++;
      lower++;

    }

  }

}
