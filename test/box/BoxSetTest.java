package box;

import org.junit.Test;
import java.util.Arrays;
import java.util.Comparator;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * This class has all the unit test for all the methods in the BoxSet class.
 */
public class BoxSetTest {

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidth() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 2, -39, 34);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, -3, 3);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddNegativeWidthAndHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(-0, -9, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroWidth() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 0, 13);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testAddRectangleWithZeroHeight() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 42, 0);
  }

  @Test
  public void testGetBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(15, 15, 5, 5);

    int[][] expected = {
            {0, 0, 10, 10},
            {15, 15, 5, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  @Test
  public void testNonOverlappingBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(15, 15, 5, 5);

    int[][] expected = {
            {0, 0, 10, 10},
            {15, 15, 5, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  @Test
  public void testOverlappingFCBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(2, 3, 3, 3);

    int[][] expected = {
            {0, 0, 2, 10},
            {2, 0, 3, 3},
            {5, 0, 5, 10},
            {2, 6, 3, 4},
            {2, 3, 3, 3}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Completely overlapped
  @Test
  public void testOverlappingCOBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-1, -1, 15, 15);

    int[][] expected = {
            {-1, -1, 15, 15},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Top left
  @Test
  public void testOverlappingTLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-2, 3, 8, 15);

    int[][] expected = {
            {0, 0, 6, 3},
            {6, 0, 4, 10},
            {-2, 3, 8, 15},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Left Middle
  @Test
  public void testOverlappingLMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-2, 3, 5, 5);

    int[][] expected = {
            {0, 0, 3, 3},
            {3, 0, 7, 10},
            {0, 8, 3, 2},
            {-2, 3, 5, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Bottom left
  @Test
  public void testOverlappingBLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-2, -3, 8, 10);

    int[][] expected = {
            {6, 0, 4, 10},
            {0, 7, 6, 3},
            {-2, -3, 8, 10},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //left half
  @Test
  public void testOverlappingLHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-2, -3, 5, 15);

    int[][] expected = {
            {3, 0, 7, 10},
            {-2, -3, 5, 15},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //middle half (on height)
  @Test
  public void testOverlappingOMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(-2, 3, 15, 5);

    int[][] expected = {
            {0, 0, 10, 3},
            {0, 8, 10, 2},
            {-2, 3, 15, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //Bottom middle
  @Test
  public void testOverlappingBMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(2, -3, 5, 5);

    int[][] expected = {
            {0, 0, 2, 10},
            {7, 0, 3, 10},
            {2, 2, 5, 8},
            {2, -3, 5, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //middle half (on width)
  @Test
  public void testOverlappingMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(2, -3, 5, 15);

    int[][] expected = {
            {0, 0, 2, 10},
            {7, 0, 3, 10},
            {2, -3, 5, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //Bottom Right
  @Test
  public void testOverlappingBRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(8, -2, 5, 7);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 5, 2, 5},
            {8, -2, 5, 7}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Right half
  @Test
  public void testOverlappingRHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(8, -2, 5, 12);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, -2, 5, 12}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Right middle
  @Test
  public void testOverlappingRMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(8, 2, 15, 4);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 0, 2, 2},
            {8, 6, 2, 4},
            {8, 2, 15, 4}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Top Right
  @Test
  public void testOverlappingTRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(8, 2, 15, 15);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 0, 2, 2},
            {8, 2, 15, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Top middle
  @Test
  public void testOverlappingTMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(4, 2, 5, 15);

    int[][] expected = {
            {0, 0, 4, 10},
            {4, 0, 5, 2},
            {9, 0, 1, 10},
            {4, 2, 5, 15}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  @Test
  public void testOverlappingAddMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(0, 0, 3, 3);
    simpleBoxSet.add(6, 0, 3, 3);
    simpleBoxSet.add(9,0,1,10);
    simpleBoxSet.add(0, 5, 10, 3);

    int[][] expected = {
            {0, 0, 3, 3},
            {6, 0, 3, 3},
            {0, 3, 3, 2},
            {0, 8, 3, 2},
            {3, 0, 3, 5},
            {3, 8, 3, 2},
            {6, 3, 3, 2},
            {6, 8, 3, 2},
            {9, 0, 1, 5},
            {9, 8, 1, 2},
            {0, 5, 10, 3}
    };
    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  @Test
  public void testOverlappingSubMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(0, 0, 3, 3);
    simpleBoxSet.subtract(6, 0, 3, 3);
    simpleBoxSet.add(9,0,1,10);
    simpleBoxSet.subtract(0, 5, 10, 3);

    int[][] expected = {
            {0, 3, 3, 2},
            {0 ,8, 3, 2},
            {3, 0, 3, 5},
            {3, 8, 3, 2},
            {9, 0, 1, 5},
            {9, 8, 1, 2},
            {6, 3, 3, 2},
            {6, 8, 3, 2}
    };
    // Sort the arrays before comparison
    // Sort based on the first element
    Arrays.sort(expected, Comparator.comparingInt(arr -> arr[0]));
    Arrays.sort(simpleBoxSet.getBoxes(), Comparator.comparingInt(arr -> arr[0]));

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //Subtract unit tests:
  @Test
  public void testSubNonOverlappingBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(15, 15, 5, 5);

    int[][] expected = {
            {0, 0, 10, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  @Test
  public void testSubOverlappingFCBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(2, 3, 3, 3);
    simpleBoxSet.subtract(2, 3, 3, 3);

    int[][] expected = {
            {0, 0, 2, 10},
            {2, 0, 3, 3},
            {5, 0, 5, 10},
            {2, 6, 3, 4}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Completely overlapped
  @Test
  public void testSubOverlappingCOBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-1, -1, 15, 15);

    int[][] expected = {

    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Top left
  @Test
  public void testSubOverlappingTLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-2, 3, 8, 15);

    int[][] expected = {
            {0, 0, 6, 3},
            {6, 0, 4, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Left Middle
  @Test
  public void testSubOverlappingLMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-2, 3, 5, 5);

    int[][] expected = {
            {0, 0, 3, 3},
            {3, 0, 7, 10},
            {0, 8, 3, 2},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());

  }

  //Bottom left
  @Test
  public void testSubOverlappingBLBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-2, -3, 8, 10);

    int[][] expected = {
            {6, 0, 4, 10},
            {0, 7, 6, 3},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //left half
  @Test
  public void testSubOverlappingLHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-2, -3, 5, 15);

    int[][] expected = {
            {3, 0, 7, 10},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //middle half (on height)
  @Test
  public void testSubOverlappingOMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(-2, 3, 15, 5);

    int[][] expected = {
            {0, 0, 10, 3},
            {0, 8, 10, 2},
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //Bottom middle
  @Test
  public void testSubOverlappingBMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(2, -3, 5, 5);

    int[][] expected = {
            {0, 0, 2, 10},
            {7, 0, 3, 10},
            {2, 2, 5, 8}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //middle half (on width)
  @Test
  public void testSubOverlappingMHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(2, -3, 5, 15);

    int[][] expected = {
            {0, 0, 2, 10},
            {7, 0, 3, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  //Bottom Right
  @Test
  public void testSubOverlappingBRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(8, -2, 5, 7);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 5, 2, 5}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Right half
  @Test
  public void testSubOverlappingRHBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(8, -2, 5, 12);

    int[][] expected = {
            {0, 0, 8, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Right middle
  @Test
  public void testSubOverlappingRMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(8, 2, 15, 4);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 0, 2, 2},
            {8, 6, 2, 4}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Top Right
  @Test
  public void testSubOverlappingTRBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(8, 2, 15, 15);

    int[][] expected = {
            {0, 0, 8, 10},
            {8, 0, 2, 2}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  // Top middle
  @Test
  public void testSubOverlappingTMBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(4, 2, 5, 15);

    int[][] expected = {
            {0, 0, 4, 10},
            {4, 0, 5, 2},
            {9, 0, 1, 10}
    };

    assertArrayEquals(expected, simpleBoxSet.getBoxes());
  }

  @Test
  public void testAddSameBox() {
    SimpleBoxSet addSameRectangle = new SimpleBoxSet();
    addSameRectangle.add(0, 0, 5, 5);
    addSameRectangle.add(0, 0, 5, 5);
    assertEquals(1, addSameRectangle.size());
  }

  @Test
  public void testZeroSet() {
    SimpleBoxSet testZeroSet = new SimpleBoxSet();
    testZeroSet.add(11, 21, 13, 21);
    testZeroSet.subtract(11, 21, 13, 21);
    assertEquals(0, testZeroSet.size());
  }

  @Test
  public void testBoxSizeAddMultipleBoxes() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.add(0, 0, 3, 3);
    simpleBoxSet.add(6, 0, 3, 3);
    simpleBoxSet.add(9,0,1,10);
    simpleBoxSet.add(0, 5, 10, 3);

    int expected = 11;

    assertEquals(expected, simpleBoxSet.size());
  }

  @Test
  public void testBoxSizeEmpty() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    int expected = 0;
    assertEquals(expected, simpleBoxSet.size());
  }

  @Test
  public void testOverlappingBoxSizeSubtract() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(0, 0, 10, 10);
    simpleBoxSet.subtract(0, 0, 3, 3);
    simpleBoxSet.subtract(6, 0, 3, 3);
    simpleBoxSet.add(9,0,1,10);
    simpleBoxSet.subtract(0, 5, 10, 3);

    int expected = 8;
    assertEquals(expected, simpleBoxSet.size());
  }

  /**
   * This test is for adding Rectangle in (-,-) plane (bottom left pane).
   */
  @Test
  public void testAddInLeftCoordinate() {
    SimpleBoxSet simpleBoxSet = new SimpleBoxSet();
    simpleBoxSet.add(-7, -7, 5, 5);
    simpleBoxSet.add(-6, -6, 1, 1);
    assertEquals(5, simpleBoxSet.size());
  }
}