package box;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This Class implements the methods in BoxSet Interface.
 */
public class SimpleBoxSet implements BoxSet {

  private ArrayList<ArrayList<Integer>> boxSet = new ArrayList<>();
  private ArrayList<Integer> tempSet = new ArrayList<>();

  /**
   * Add a given rectangle to this set, and make this set the result.
   * @param x the x-coordinate of the rectangle to be added
   * @param y the y-coordinate of the rectangle to be added
   * @param width the width of the rectangle to be added
   * @param height the height of the rectangle to be added
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void add(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid Width/Height provided");
    }

    for (int i = 0; i < boxSet.size(); i++) {

      if (isOverlapping(i, x, y, width, height)) {
        // find intersecting region between given box and the box in boxSet
        ArrayList<Integer> intersection = calculateCommonArea(i, x, y, width, height);
        makeNewFigure(i, intersection.get(0), intersection.get(1),
                intersection.get(2), intersection.get(3));
      }
    }

    // Remove the old box
    for (int i = tempSet.size() - 1; i > -1; i--) {
      int removeIndex = tempSet.get(i);
      boxSet.remove(removeIndex);
    }
    tempSet = new ArrayList<>();

    // Create and Add new box
    ArrayList<Integer> newRect = new ArrayList<>(Arrays.asList(x, y, width, height));
    boxSet.add(newRect);

  }

  /**
   * Subtract the given rectangle from this set, and make this set the result.
   * @param x the x-coordinate of the rectangle to be subtracted
   * @param y the y-coordinate of the rectangle to be subtracted
   * @param width the width of the rectangle to be subtracted
   * @param height the height of the rectangle to be subtracted
   * @throws IllegalArgumentException if the width or height of the rectangle are not positive
   */
  @Override
  public void subtract(int x, int y, int width, int height) throws IllegalArgumentException {
    if (width <= 0 || height <= 0) {
      throw new IllegalArgumentException("Invalid Width/Height provided");
    }

    for (int i = 0; i < boxSet.size(); i++) {
      if (isOverlapping(i, x, y, width, height)) {
        // find intersecting region between given box and the box in boxSet
        ArrayList<Integer> intersection = calculateCommonArea(i, x, y, width, height);
        makeNewFigure(i, intersection.get(0), intersection.get(1),
                intersection.get(2), intersection.get(3));
      }
    }

    // CleanUp - Remove the old box
    for (int i = tempSet.size() - 1; i > -1; i--) {
      int removeIndex = tempSet.get(i);
      boxSet.remove(removeIndex);
    }
    tempSet = new ArrayList<>();
  }

  /**
   * Returns the list of rectangles of the format (x, y , width, height)
   *        available in the set of Rectangles.
   * @return returns an array integers of the format (x, y , width, height)
   *        for all available rectangles.
   */
  @Override
  public int[][] getBoxes() {
    int[][] boxesArray = new int[boxSet.size()][4];

    for (int i = 0; i < boxSet.size(); i++) {
      ArrayList<Integer> box = boxSet.get(i);
      boxesArray[i][0] = box.get(0);  // x
      boxesArray[i][1] = box.get(1);  // y
      boxesArray[i][2] = box.get(2);  // width
      boxesArray[i][3] = box.get(3);  // height
    }
    return boxesArray;
  }

  /**
   * Returns the number of  rectangles in the boxSet.
   * @return returns the size of the set of rectangles as int
   */
  @Override
  public int size() {
    return boxSet.size();
  }

  /**
   * This method checks if the two regions are overlapping or not
   *        and returns boolean true/false respectively.
   * @param i index of the box in boxSet
   * @param x x-co-ordinate of the new box
   * @param y y-co-ordinate of the new box
   * @param width width of the new box
   * @param height height of the new box
   * @return returns boolean true if the new box overlaps with the ith Box else boolean false
   */
  private boolean isOverlapping(int i, int x, int y, int width, int height) {
    // Check for x overlap
    boolean xOvp = (boxSet.get(i).get(0) < x + width)
            && (x < boxSet.get(i).get(0) + boxSet.get(i).get(2));

    // Check if the y overlap
    boolean yOvp = (boxSet.get(i).get(1) < y + height)
            && (y < boxSet.get(i).get(1) + boxSet.get(i).get(3));

    // The rectangles overlap if and only if both x and y projections overlap
    return xOvp && yOvp;
  }

  /**
   * This method calculates the commonRegion between boxSet(i) and
   * new box (x2, y2, width2, height2).
   * @param i index of the box in boxSet
   * @param x2 x-co-ordinate of the new box
   * @param y2 y-co-ordinate of the new box
   * @param width2 width of the new box
   * @param height2 height of the new box
   * @return returns diagonal corners(bottom left and top right) co-ordinates of the common region
   */
  private ArrayList<Integer> calculateCommonArea(int i, int x2, int y2, int width2, int height2) {
    int x1 = boxSet.get(i).get(0);
    int y1 = boxSet.get(i).get(1);
    int width1 = boxSet.get(i).get(2);
    int height1 = boxSet.get(i).get(3);

    // Calculate the coordinates of the overlapping rectangle
    int xOverlap = Math.max(x1, x2);
    int yOverlap = Math.max(y1, y2);
    int xEndOverlap = Math.min(x1 + width1, x2 + width2);
    int yEndOverlap = Math.min(y1 + height1, y2 + height2);

    return new ArrayList<>(Arrays.asList(xOverlap, yOverlap, xEndOverlap, yEndOverlap));

  }


  /**
   *  This method breaks the figure into more boxes if there is an overlap
   *   vertically first and then horizontally.
   * @param i index of the box in boxSet
   * @param x x-co-ordinate of the bottom left common region
   * @param y y-co-ordinate of the bottom left common region
   * @param x4 x-co-ordinate of the top right corner of the common region
   * @param y4 y-co-ordinate of the top right corner of the common region
   */
  private void makeNewFigure(int i, int x, int y, int x4, int y4) {
    int newX;
    int newY;
    int newWidth;
    int newHeight;

    //Left bottom of common area
    newX = boxSet.get(i).get(0);
    newY = boxSet.get(i).get(1);
    newWidth = x - newX;

    if (newWidth > 0) {
      newHeight = boxSet.get(i).get(3);
      //Add the box
      boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
    }
    else {
      newHeight =  y - boxSet.get(i).get(1);
      if (newHeight > 0) {
        newWidth = x4 - newX;
        boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
      }
    }

    //Right bottom of common Area
    if (boxSet.get(i).get(0) + boxSet.get(i).get(2) == x4 && (boxSet.get(i).get(0) != x) ) {
      //only one box exists
      newHeight = y - boxSet.get(i).get(1);
      if (newHeight > 0) {
        newX = x;
        newY = boxSet.get(i).get(1);
        newWidth = x4 - x;
        boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
      }
    }
    else if (boxSet.get(i).get(0) + boxSet.get(i).get(2) > x4) {
      // might have to add 2 boxes
      // middle bottom
      if (boxSet.get(i).get(0) < x && (y - boxSet.get(i).get(1) > 0) ) {
        newX = x;
        newY = boxSet.get(i).get(1);
        newWidth = x4 - x;
        newHeight = y - boxSet.get(i).get(1);
        //Add the box
        boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
      }

      //right bottom
      newX = x4;
      newY = boxSet.get(i).get(1);
      newWidth = boxSet.get(i).get(0) + boxSet.get(i).get(2) - x4;
      newHeight = boxSet.get(i).get(3);
      //Add the box
      boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
    }

    //Top Left
    if (x == boxSet.get(i).get(0)) {
      if (boxSet.get(i).get(1) + boxSet.get(i).get(3) > y4) {
        newX = x;
        newY = y4;
        newHeight = boxSet.get(i).get(1) + boxSet.get(i).get(3) - y4;
        newWidth = x4 - x;
        //Add the box
        boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
      }
    }
    else {
      //top middle
      if (x > boxSet.get(i).get(0)  && x4 < boxSet.get(i).get(0) + boxSet.get(i).get(2)) {
        newX = x;
        newY = y4;
        newWidth = x4 - x;
        newHeight = boxSet.get(i).get(1) + boxSet.get(i).get(3) - y4;
        if (newHeight > 0) {
          //Add the box
          boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
        }
      }

      //top right
      else if (x > boxSet.get(i).get(0)  && x4 == boxSet.get(i).get(0) + boxSet.get(i).get(2)) {
        newX = x;
        newY = y4;
        newWidth = x4 - x;
        newHeight = boxSet.get(i).get(1) + boxSet.get(i).get(3) - y4;
        if (newHeight > 0) {
          //Add the box
          boxSet.add(new ArrayList<>(Arrays.asList(newX, newY, newWidth, newHeight)));
        }
      }

    }
    tempSet.add(i);
  }
}
