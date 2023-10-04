package sim;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

/**
 * SimplePoolSimulator represents a simple pool game simulator
 *        implementing the PoolSimulator interface.
 * It provides functionalities to start the game, advance the simulation,
 *        and retrieve information about the table and cue ball.
 * The simulation is based on specified pool table types: "friction" and "simple".
 */
public class SimplePoolSimulator implements PoolSimulator {

  private final List<String> tableTypes = Arrays.asList("friction", "simple");

  PoolTable table;
  Ball cueBall;
  protected StatusMessage status = new StatusMessage();

  /**
   * Constructs a SimplePoolSimulator with the specified width, height, and table type.
   *
   * @param width  The width of the pool table.
   * @param height The height of the pool table.
   * @param type   The type of the pool table: "friction" or "simple".
   * @throws IllegalArgumentException If the width or height is less than or equal to 0,
   *                                  or if the specified table type is not valid.
   */
  public SimplePoolSimulator(int width, int height, String type) throws IllegalArgumentException {

    if ( width <= 0 || height <= 0 ) {
      throw new IllegalArgumentException("Invalid pool width/height");
    }
    if ( !tableTypes.contains(type) ) {
      throw new IllegalArgumentException("Invalid table type");
    }

    if (Objects.equals(type, "friction")) {
      table = new NewtonianTable(width, height, type, status);
    }
    else {
      table = new SimpleTable(width, height, type, status);
    }

  }

  @Override
  public void start(int x, int y, int radius, int speed, double dx, double dy)
          throws IllegalArgumentException {

    if (table == null) {
      throw new IllegalStateException("Create Table first");
    }
    
    if (radius <= 0 || speed < 0
            || radius >= Math.min(table.tableHeight, table.tableWidth)) {
      throw new IllegalArgumentException("Invalid radius/speed");
    }

    if (x > table.tableWidth - radius || x < radius
            ||  y > table.tableHeight - radius || y < radius) {
      throw new IllegalArgumentException("Ball is outside the pool");
    }

    cueBall = new Ball(x, y, radius, speed, dx, dy);
    status.currentStatus = 1;
  }

  @Override
  public void advance() throws IllegalStateException {
    if (cueBall == null) {
      throw new IllegalStateException("cueBall is not placed yet!");
    }
    table.move(cueBall);
  }

  @Override
  public int getTableWidth() {
    if (table == null) {
      throw new IllegalStateException("Table is has not been chosen yet!");
    }
    return table.tableWidth;
  }

  @Override
  public int getTableHeight() {
    if (table == null) {
      throw new IllegalStateException("Table is has not been chosen yet!");
    }
    return table.tableHeight;
  }

  @Override
  public double getBallPositionX() {
    if (cueBall == null) {
      return  Double.NEGATIVE_INFINITY;
    }
    return cueBall.x;
  }

  @Override
  public double getBallPositionY() {
    if (cueBall == null) {
      return  Double.NEGATIVE_INFINITY;
    }
    return cueBall.y;
  }

  @Override
  public double getBallRadius() {
    if (cueBall == null) {
      return  Double.NEGATIVE_INFINITY;
    }
    return cueBall.radius;
  }

  @Override
  public double getBallVelocityX() {
    if (cueBall == null) {
      return  0;
    }
    return cueBall.vx;
  }

  @Override
  public double getBallVelocityY() {
    if (cueBall == null) {
      return  0;
    }
    return cueBall.vy;
  }

  @Override
  public String getStatus() {
    return "Status: " + status.statusStrings.get(status.currentStatus);
  }

}
