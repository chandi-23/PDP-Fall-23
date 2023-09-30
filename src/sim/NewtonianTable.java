package sim;

import java.util.Map;
import java.util.Objects;

public class NewtonianTable extends PoolTable{

  private double coefFr = 0.1;
  public NewtonianTable(int width, int height, String type, statusMessage status){
    super(width, height, type, status);
  }

  private double solveForT(double a, double b, double c) {
    System.out.println("solving for t: " + a +" " + b + " " + c);
    double delta = (b * b) - (4 * a * c);

    if (delta < 0){
      System.out.println("Delta < 0");
      return Double.POSITIVE_INFINITY;
    }

      double time1 = ( (-b + Math.sqrt(delta)) / (2 * a)  );
      double time2 = (  (-b - Math.sqrt(delta)) / (2 * a) );
      System.out.println("values for T: " + time1 + " " + time2);
    // Return the minimum positive root
    if (time1 > 0 && (time2 <= 0 || time1 < time2)) {
      return time1;
    } else if (time2 > 0) {
      return time2;
    } else {
      // Both roots are non-positive, return a designated value (e.g., Double.MAX_VALUE)
      return Double.NEGATIVE_INFINITY;
    }

  }

  protected Map.Entry<String, Double> getClosestSide(Ball cueBall){
    distances.clear();
    double time;
    if(cueBall.dx > 0) {
      System.out.println("For +x");
      time = solveForT(-0.5 * coefFr * 9.81 * cueBall.dx,
              cueBall.vx,
              -(tableWidth - cueBall.x - cueBall.radius));
      System.out.println("For +x:"+time);
      distances.put("+x", time);

    }
    else if (cueBall.dx < 0){
      time = solveForT(-0.5 * coefFr * 9.81 * cueBall.dx,
              cueBall.vx,
              cueBall.x - cueBall.radius);
      distances.put("-x", time);
    }

    if(cueBall.dy > 0) {
      System.out.println("For +y");
      time = solveForT(-0.5 * coefFr * 9.81 * cueBall.dy,
              cueBall.vy,
              -(tableHeight - cueBall.y - cueBall.radius));
      distances.put("+y", time);

    }
    else if (cueBall.dy < 0){
      time = solveForT((-0.5 * coefFr * 9.81 * cueBall.dy),
              (cueBall.vy),
              (cueBall.y - cueBall.radius) );
      System.out.println("from -y:" +time);
      distances.put("-y", time);
    }

    System.out.println(distances);
//    distances.put("+x", Double.NEGATIVE_INFINITY);
//    distances.put("+y", 12.0);
    Map.Entry<String, Double> minEntry = null;

    for (Map.Entry<String, Double> entry : distances.entrySet()) {
      if (minEntry == null || entry.getValue() < minEntry.getValue()) {
        minEntry = entry;
      }
    }
    return minEntry;
  }
  protected void move (Ball cueBall) {
    if (cueBall.speed > 0) {
      Map.Entry<String, Double> closest = getClosestSide(cueBall);
      if (closest != null) {
        String cSide = closest.getKey();
        double sTime = closest.getValue();
        System.out.println("shortest time:" + sTime);
        System.out.println("============Checking for vx vy: " +cueBall.vx + "  "+ cueBall.vy);

        if (sTime != Double.POSITIVE_INFINITY && sTime != Double.NEGATIVE_INFINITY) {
          if (Objects.equals(cSide, "+x")) {
            cueBall.y += (cueBall.vy * sTime) - (0.5 * coefFr * 9.81 * cueBall.dy * sTime *sTime);
            cueBall.x = tableWidth - cueBall.radius;
            cueBall.vx = -(cueBall.vx - ( coefFr * 9.81 * cueBall.dx * sTime));
            cueBall.vy = cueBall.vy - ( coefFr * 9.81 * cueBall.dy * sTime);
            cueBall.dx = -cueBall.dx;
            status.currentStatus = 3;
            System.out.println("Hit the right edge in: " + sTime);

          } else if (Objects.equals(cSide, "-x")) {
            cueBall.y += (cueBall.vy * sTime) - (0.5 * coefFr * 9.81 * cueBall.dy * sTime * sTime);
            cueBall.x = cueBall.radius;
            cueBall.vx = -(cueBall.vx - (coefFr * 9.81 * cueBall.dx * sTime));
            cueBall.vy = cueBall.vy - (coefFr * 9.81 * cueBall.dy * sTime);
            cueBall.dx = -cueBall.dx;
            status.currentStatus = 2;
            System.out.println("Hit the left edge in: " + sTime + " " + (cueBall.speed * cueBall.dy));

          } else if (Objects.equals(cSide, "+y")) {
            cueBall.x += (cueBall.vx * sTime) - (0.5 * coefFr * 9.81 * cueBall.dx * sTime *sTime);
            cueBall.y = tableHeight - cueBall.radius;
            cueBall.vx = cueBall.vx - ( coefFr * 9.81 * cueBall.dx * sTime);
            cueBall.vy = -(cueBall.vy - ( coefFr * 9.81 * cueBall.dy * sTime));
            cueBall.dy = -cueBall.dy;
            status.currentStatus = 4;
            System.out.println("Hit the top edge in: " + sTime);

          } else {
            System.out.println("Hit the bottom edge in:  " + sTime + " vx:" + cueBall.vx + " x:" +cueBall.x);
            cueBall.x += (cueBall.vx * sTime) - (0.5 * coefFr * 9.81 * cueBall.dx * sTime *sTime);
            cueBall.y = cueBall.radius;
            cueBall.vx = cueBall.vx - (coefFr * 9.81 * cueBall.dx * sTime);
            cueBall.vy = -(cueBall.vy - (coefFr * 9.81 * cueBall.dy * sTime));
            cueBall.dy = -cueBall.dy;

            status.currentStatus = 5;

          }
        }
        // for Infinities
        else {
          //System.out.println("Resting");
          status.currentStatus = 6;
          sTime = cueBall.vx/(0.5 * 9.81 * coefFr * cueBall.dx);
          double sTime2 = cueBall.vy/(0.5 * 9.81 * coefFr * cueBall.dy);
          System.out.println(" IN stations:"+sTime + "  "+sTime2);
          if(cueBall.dx !=0) {
            cueBall.x += (cueBall.vx * cueBall.vx) / (2 * coefFr * 9.81 * cueBall.dx);
          }
          if (cueBall.dy !=0 ) {
            cueBall.y += (cueBall.vy * cueBall.vy)/ (2 * coefFr * 9.81 * cueBall.dy);
          }
          cueBall.vx = 0;
          cueBall.vy = 0;
          cueBall.speed =0;
        }
      }
    }
    else{
      status.currentStatus = 6;
    }

    if(cueBall.x > 400 || cueBall.y> 400 || cueBall.x <0 || cueBall.y < 0){
      System.out.println("Ball out of index");
    }

  }
}
