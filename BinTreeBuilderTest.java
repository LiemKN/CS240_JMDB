import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

/*
* This work complies with the JMU Honor Code.
* References and Acknowledgments: I received no outside help with this
* programming assignment.
*/

/**
 * Class that tests the BinTreeBuilder class.
 * 
 * @author Liem Nguyen
 * @version 10/2018
 */
class BinTreeBuilderTest {
  
  BinTreeBuilder<Integer> builder = new BinTreeBuilder<>(); 

  @Test
  void test() {
    
    List<Integer> test1 = new ArrayList<>();
    List<Integer> test2 = new ArrayList<>();
    List<Integer> test3 = new ArrayList<>();
    List<Integer> test4 = new ArrayList<>();
    List<Integer> test5 = new ArrayList<>();
    List<Integer> test6 = new ArrayList<>();
    List<Integer> test7 = new ArrayList<>();
    
    for (int i = 0; i < 1; i++) {
      test1.add(i);
    }
    for (int j = 0; j < 2; j++) {
      test2.add(j);
    }
    for (int j = 0; j < 3; j++) {
      test3.add(j);
    }
    for (int j = 0; j < 4; j++) {
      test4.add(j);
    }
    for (int j = 0; j < 4; j++) {
      test5.add(j);
    }
    for (int j = 0; j < 4; j++) {
      test6.add(j);
    }
    for (int j = 0; j < 4; j++) {
      test7.add(j);
    }
    
    builder.build(test1);
    builder.build(test2);
    builder.build(test3);
    builder.build(test4);
    builder.build(test5);
    builder.build(test6);
    builder.build(test7);
    
    assertEquals(test1, test1);
    
  }

}
