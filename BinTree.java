import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Generic immutable binary tree node. Although the code does not enforce it,
 * the formed tree should satisfty the binary search tree property: The left
 * sub-tree contains all values that are strictly smaller than the current
 * element; the right sub-tree contains values that are greater than or equal to
 * the current element.
 *
 * @author Michael S. Kirkpatrick & Liem Nguyen
 * @version V1, 9/2018
 */

public class BinTree<E> {
  E element;
  BinTree<E> left;
  BinTree<E> right;
  List<E> result = new ArrayList<>();

  /**
   * Create a new BinTree that stores the item in the curent node.
   *
   * @param item
   *          The element to store in this node
   * @param left
   *          The root of the left sub-tree
   * @param right
   *          The root of the right sub-tree
   */
  public BinTree(E item, BinTree<E> left, BinTree<E> right) {
    this.element = item;
    this.left = left;
    this.right = right;
  }

  /**
   * Create a new BinTree that stores the item in the curent node. Sets the right
   * sub-tree to null.
   *
   * @param item
   *          The element to store in this node
   * @param left
   *          The root of the left sub-tree
   */
  public BinTree(E item, BinTree<E> left) {
    this(item, left, null);
  }

  /**
   * Create a new BinTree that stores the item in the curent node. Sets the both
   * sub-trees to null.
   *
   * @param item
   *          The element to store in this node
   */
  public BinTree(E item) {
    this(item, null, null);
  }

  /**
   * Get the BinTree node's element.
   *
   * @return The element stored in this BinTree.
   */
  public E getElement() {
    return element;
  }

  /**
   * Get the left sub-tree.
   *
   * @return The root of the left sub-tree if it exists.
   */
  public BinTree<E> getLeft() {
    return left;
  }

  /**
   * Get the right sub-tree.
   *
   * @return The root of the right sub-tree if it exists.
   */
  public BinTree<E> getRight() {
    return right;
  }

  /**
   * Search through a binary tree for nodes that match a particular criterion. If
   * the passed function returns 0, the current BinTree's element matches the
   * criterion. If the passed function returns a negative value, the current
   * element is too small, and only the right sub-tree needs searched. If the
   * value returned is positive, the current element is too large, and only the
   * left sub-tree needs searched.
   *
   * @param criterion
   *          The function specifying the match criterion.
   * @return A list of matching elements from an in-order traversal.
   */
  public List<E> search(Function<E, Integer> criterion) {

    /* Determine which half of the BinTree to search based off the criterion. */
    if (criterion.apply(element) == -1) {

      if (right != null) {

        result = right.search(criterion);

      }

    } else if (criterion.apply(element) == 1) {

      if (left != null) {

        result = left.search(criterion);

      }

      /* If criterion fits, add it to the result list to be returned later. */
    } else {

      result.add(element);

      if (left != null) {

        /*
         * Make a temporary sub list to hold additional found movies that fit the
         * criterion.
         */
        List<E> leftMatch = left.search(criterion);

        /*
         * Copy the elements of the sub list into the main result list to be returned
         * later.
         */
        for (int i = 0; i < leftMatch.size(); i++) {

          result.add(leftMatch.get(i));

        }

      }

      if (right != null) {

        /*
         * Make a temporary sub list to hold additional found movies that fit the
         * criterion.
         */
        List<E> rightMatch = right.search(criterion);

        /*
         * Copy the elements of the sub list into the main result list to be returned
         * later.
         */
        for (int i = 0; i < rightMatch.size(); i++) {

          result.add(rightMatch.get(i));

        }

      }

    }

    return result;

  }

}
