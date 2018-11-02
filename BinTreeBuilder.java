import java.util.ArrayList;
import java.util.List;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Builds an immutable binary tree from a sorted list. Building the tree should
 * be accomplished recursively so that an in-order traversal returns the same
 * order of elements. One way to to this is to set the root element to the
 * middle element of that portion of the list. The left sub-tree is then created
 * from all elements preceding the root in the list, while the right sub-tree is
 * the later elements. For example, consider the list 0 1 2 3 4 5 6 7 8. The
 * resulting tree should look as follows:
 *
 * <p>4 / \ 1 6 / \ / \ 0 2 5 7 \ \ 3 8
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
public class BinTreeBuilder<E> {

  /**
   * Builds the tree from the given list.
   *
   * @param list
   *          The list of elements to form the tree with.
   * @return The root of the tree.
   */
  public BinTree<E> build(List<E> list) {

    /* Only works if given list is in ascending order. */
    E root = list.get((list.size() - 1) / 2);

    List<E> less = new ArrayList<>();
    List<E> more = new ArrayList<>();

    /* Fill new lists be used on the recursive call. */
    for (int i = 0; i < (list.size() - 1) / 2; i++) {

      less.add(list.get(i));

    }

    for (int i = ((list.size() - 1) / 2) + 1; i < list.size(); i++) {

      more.add(list.get(i));

    }

    /*
     * Base cases for newly created lists otherwise continue building the tree
     * recursively.
     */
    if (less.size() == 0 && more.size() == 0) {

      return new BinTree<E>(root);

    } else if (less.size() == 0 && more.size() == 1) {

      return new BinTree<E>(root, null, new BinTree<E>(more.get(0)));

    } else if (less.size() == 1 && more.size() == 1) {

      return new BinTree<E>(root, new BinTree<E>(less.get(0)), new BinTree<E>(more.get(0)));

    } else {

      return new BinTree<E>(root, build(less), build(more));

    }

  }

}
