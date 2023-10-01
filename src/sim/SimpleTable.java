package sim;

import java.util.Map;
import java.util.Objects;

/**
 * SimpleTable is a subclass of PoolTable representing a
 *        simple pool table in the simulation.
 * It provides methods to determine the closest side,
 *        handle cue ball movement, and manage collision loss.
 */
public class SimpleTable extends PoolTable {

  private final int collisionLoss = 5;

  /**
   * Constructs a SimpleTable with the specified width, height, table type, and status message.
   *
   * @param width   The width of the table.
   * @param height  The height of the table.
   * @param type    The type of table.
   * @param status  The status message object to track the simulation status.
   */
  public SimpleTable(int width, int height, String type, StatusMessage status) {
    super(width, height, type, status);
  }

  /**
   * Determines the closest side of the table to the
   *        cue ball and calculates the time to reach that side.
   *
   * @param cueBall The cue ball object.
   * @return A Map.Entry representing the closest side and its corresponding time duration.
   */
  protected Map.Entry<String, Double> getClosestSide(Ball cueBall) {
    duration.clear();

    double dx = cueBall.dx;
    double dy = cueBall.dy;
    double speed = cueBall.speed;

    if (dx != 0) {
      double xDuration = Math.abs((dx > 0 ? tableWidth - cueBall.x - cueBall.radius
              : cueBall.x - cueBall.radius) / (speed * dx));
      duration.put((dx > 0) ? "+x" : "-x", xDuration);
    }

    if (dy != 0) {
      double yDuration = Math.abs((dy > 0 ? tableHeight - cueBall.y - cueBall.radius
              : cueBall.y - cueBall.radius) / (speed * dy));
      duration.put((dy > 0) ? "+y" : "-y", yDuration);
    }

    return duration.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .orElse(null);
  }


  /**
   * Moves the cue ball based on the closest side and handles collisions.
   *
   * @param cueBall The cue ball object.
   */
  protected void move(Ball cueBall) {
    Map.Entry<String, Double> closest = getClosestSide(cueBall);

    if (cueBall.speed <= 0 || closest == null) {
      cueBall.speed = 0;
      cueBall.vx = 0;
      cueBall.vy = 0;
      status.currentStatus = 6;
      return;
    }

    String cSide = closest.getKey();
    double sTime = closest.getValue();

    if (Objects.equals(cSide, "+x") || Objects.equals(cSide, "-x")) {
      cueBall.y += cueBall.speed * cueBall.dy * sTime;
      cueBall.x = Objects.equals(cSide, "+x") ? tableWidth - cueBall.radius : cueBall.radius;
      cueBall.dx = -cueBall.dx;
      status.currentStatus = Objects.equals(cSide, "+x") ? 3 : 2;
    } else {
      cueBall.x += cueBall.speed * cueBall.dx * sTime;
      cueBall.y = Objects.equals(cSide, "+y") ? tableHeight - cueBall.radius : cueBall.radius;
      cueBall.dy = -cueBall.dy;
      status.currentStatus = Objects.equals(cSide, "+y") ? 4 : 5;
    }

    cueBall.speed -= collisionLoss;

    if (cueBall.speed <= 0) {
      cueBall.vx = 0;
      cueBall.vy = 0;
      status.currentStatus = 6;
    } else {
      cueBall.vx = cueBall.speed * cueBall.dx;
      cueBall.vy = cueBall.speed * cueBall.dy;
    }
  }

}



