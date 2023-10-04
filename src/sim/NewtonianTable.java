package sim;

import java.util.Map;
import java.util.Objects;

/**
 * NewtonianTable represents a pool table simulation with Newtonian physics.
 * It extends the PoolTable class and overrides methods to handle physics
 * calculations specific to Newtonian behavior.
 */
public class NewtonianTable extends PoolTable {

  private static final double coefFr = 0.1; //Coefficient of friction for the table.

  /**
   * Constructs a NewtonianTable with the specified dimensions and type of table.
   *
   * @param width   The width of the table.
   * @param height  The height of the table.
   * @param type    The type of table.
   * @param status  The status message object to track the simulation status.
   */
  public NewtonianTable(int width, int height, String type, StatusMessage status) {
    super(width, height, type, status);
  }

  /**
   * Solves for time given quadratic equation parameters.
   *
   * @param a The coefficient of t^2 in the quadratic equation.
   * @param b The coefficient of t in the quadratic equation.
   * @param c The constant term in the quadratic equation.
   * @return The time(s) at which the quadratic equation is satisfied.
   */
  private double solveForT(double a, double b, double c) {
    double delta = (b * b) - (4 * a * c);

    if (delta < 0) {

      return Double.POSITIVE_INFINITY;
    }

    double time1 = ( (-b + Math.sqrt(delta)) / (2 * a)  );
    double time2 = (  (-b - Math.sqrt(delta)) / (2 * a) );

    if (time1 > 0 && (time2 <= 0 || time1 < time2)) {
      return time1;
    } else if (time2 > 0) {
      return time2;
    } else {
      return Double.NEGATIVE_INFINITY;
    }

  }

  /**
   * Finds the closest side of the table that the cue ball will hit.
   *
   * @param cueBall The cue ball object.
   * @return Map.Entry representing the closest side and the time it takes to hit that side.
   */
  protected Map.Entry<String, Double> getClosestSide(Ball cueBall) {
    duration.clear();
    double time;
    double a;
    double b;
    double c;

    if (cueBall.dx !=  0) {
      a = -0.5 * coefFr * 9.81 * cueBall.dx;
      b = cueBall.vx;
      c = (cueBall.dx > 0) ? -(tableWidth - cueBall.x - cueBall.radius)
              : (cueBall.x - cueBall.radius);

      time = solveForT(a, b , c);
      duration.put((cueBall.dx > 0) ? "+x" : "-x", time);
    }

    if (cueBall.dy != 0) {
      a = -0.5 * coefFr * 9.81 * cueBall.dy;
      b = cueBall.vy;
      c = (cueBall.dy > 0) ? -(tableHeight - cueBall.y - cueBall.radius)
              : (cueBall.y - cueBall.radius);

      time = solveForT(a, b , c);
      duration.put((cueBall.dy > 0) ? "+y" : "-y", time);
    }

    return duration.entrySet()
            .stream()
            .min(Map.Entry.comparingByValue())
            .orElse(null);
  }

  /**
   * Moves the cue ball based on Newtonian physics, handling collisions and updates its status.
   *
   * @param cueBall The cue ball object.
   */
  protected void move(Ball cueBall) {
    Map.Entry<String, Double> closest = getClosestSide(cueBall);

    if (cueBall.speed < 0 || closest == null) {
      status.currentStatus = 0;
      return;
    }

    String cSide = closest.getKey();
    double sTime = closest.getValue();

    if (sTime != Double.POSITIVE_INFINITY && sTime != Double.NEGATIVE_INFINITY) {

      if (Objects.equals(cSide, "+x") || Objects.equals(cSide, "-x") ) {
        cueBall.y += (cueBall.vy * sTime) - (0.5 * coefFr * 9.81 * cueBall.dy * sTime * sTime);
        cueBall.vx = -(cueBall.vx - ( coefFr * 9.81 * cueBall.dx * sTime));
        cueBall.vy = cueBall.vy - ( coefFr * 9.81 * cueBall.dy * sTime);
        cueBall.dx = -cueBall.dx;

        cueBall.x = Objects.equals(cSide, "+x") ? tableWidth - cueBall.radius : cueBall.radius;
        status.currentStatus = Objects.equals(cSide, "+x") ? 3 : 2;
      }
      else if (Objects.equals(cSide, "+y") || Objects.equals(cSide, "-y")) {
        cueBall.x += (cueBall.vx * sTime) - (0.5 * coefFr * 9.81 * cueBall.dx * sTime * sTime);
        cueBall.vx = cueBall.vx - ( coefFr * 9.81 * cueBall.dx * sTime);
        cueBall.vy = -(cueBall.vy - ( coefFr * 9.81 * cueBall.dy * sTime));
        cueBall.dy = -cueBall.dy;

        cueBall.y = Objects.equals(cSide, "+y") ? tableHeight - cueBall.radius : cueBall.radius;
        status.currentStatus = Objects.equals(cSide, "+y") ? 4 : 5;
      }
    }

    // for Infinities ie ball will not reach any edges
    else {
      status.currentStatus = 6;
      if (cueBall.dx != 0) {
        cueBall.x += (cueBall.vx * cueBall.vx) / (2 * coefFr * 9.81 * cueBall.dx);
      }
      if (cueBall.dy != 0) {
        cueBall.y += (cueBall.vy * cueBall.vy) / (2 * coefFr * 9.81 * cueBall.dy);
      }
      cueBall.vx = 0;
      cueBall.vy = 0;
      cueBall.speed = 0;
    }
  }

}
