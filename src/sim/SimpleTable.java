package sim;

import javax.swing.plaf.synth.SynthTextAreaUI;
import java.awt.*;
import java.util.Map;
import java.util.Objects;

public class SimpleTable extends PoolTable{

  private final int collisionLoss = 5;

  public SimpleTable(int width, int height, String type, statusMessage status){
    super(width, height, type, status);
  }


  protected Map.Entry<String, Double> getClosestSide(Ball cueBall) {
    distances.clear();

    if (cueBall.dx > 0) {
      distances.put("+x", Math.abs((tableWidth - cueBall.x - cueBall.radius) / (cueBall.speed * cueBall.dx)) );
    } else {
      distances.put("-x", Math.abs((cueBall.x - cueBall.radius) / (cueBall.speed * cueBall.dx)));
    }

    if (cueBall.dy > 0) {
      distances.put("+y", Math.abs((tableWidth - cueBall.y - cueBall.radius) / (cueBall.speed * cueBall.dy)));
    } else {
      distances.put("-y", Math.abs((cueBall.y - cueBall.radius) / (cueBall.speed * cueBall.dy)));
    }
    Map.Entry<String, Double> minEntry = null;

    for (Map.Entry<String, Double> entry : distances.entrySet()) {
      if (minEntry == null || entry.getValue() < minEntry.getValue()) {
        minEntry = entry;
      }
    }
    return minEntry;
  }

  @Override
  protected void move(Ball cueBall) {
    if (cueBall.speed > 0) {
      Map.Entry<String, Double> closest = getClosestSide(cueBall);

      if (closest != null) {
        String cSide = closest.getKey();
        double sTime = closest.getValue();
        //System.out.println("sTime: " + sTime);
        //System.out.println(closest.getKey());
        if (Objects.equals(cSide, "+x")) {

          cueBall.y += cueBall.speed * cueBall.dy * sTime;
          cueBall.x = tableWidth - cueBall.radius;
          cueBall.dx = -cueBall.dx;
          status.currentStatus = 3;
          //System.out.println("Hit the right edge in: " + sTime);
          //System.out.println("x: "+ cueBall.x +" y: "+ cueBall.y + " dx:" + cueBall.dx + " dy:" + cueBall.dy );
        }
        else if (Objects.equals(cSide, "-x")) {
          cueBall.y += cueBall.speed * cueBall.dy * sTime;
          cueBall.x = cueBall.radius;
          cueBall.dx = -cueBall.dx;
          status.currentStatus = 2;
//          System.out.println("Hit the left edge in: " + sTime + " " + (cueBall.speed * cueBall.dy));

        }
        else if (Objects.equals(cSide, "+y")) {
          cueBall.x += cueBall.speed * cueBall.dx * sTime;
          cueBall.y = tableHeight - cueBall.radius;
          cueBall.dy = -cueBall.dy;
          status.currentStatus = 4;
          //System.out.println("Hit the top edge in: " + sTime);
        }
        else if (Objects.equals(cSide, "-y")){
          cueBall.x += cueBall.speed * cueBall.dx * sTime;
          cueBall.y = cueBall.radius;
          cueBall.dy = -cueBall.dy;
          status.currentStatus = 5;
          //System.out.println("Hit the bottom edge in:  " +sTime + " " + (cueBall.speed * cueBall.dy));
        }

        cueBall.speed -= collisionLoss;
        if (cueBall.speed <=0){
          cueBall.vx = 0;//(cueBall.dx/ Math.sqrt(cueBall.dx*cueBall.dx + cueBall.dy*cueBall.dy));
          cueBall.vy = 0;
          status.currentStatus = 6;
        }
        else{
          cueBall.vx = cueBall.speed *  cueBall.dx;//(cueBall.dx/ Math.sqrt(cueBall.dx*cueBall.dx + cueBall.dy*cueBall.dy));
          cueBall.vy = cueBall.speed *  cueBall.dy; //(cueBall.dy/ Math.sqrt(cueBall.dx*cueBall.dx + cueBall.dy*cueBall.dy));
          System.out.println("New Speed:" + cueBall.speed);

        }
        //System.out.println(" speed:" +cueBall.speed + " vx: " +cueBall.vx + " vy: " +cueBall.vy);
      }
      else {
        System.out.println("No stime left");
        cueBall.speed = 0;
        cueBall.vx = 0;//cueBall.speed *  cueBall.dx;//(cueBall.dx/ Math.sqrt(cueBall.dx*cueBall.dx + cueBall.dy*cueBall.dy));
        cueBall.vy = 0;//cueBall
        status.currentStatus = 6;
      }
    }
    else{
      cueBall.speed = 0;
      cueBall.vx = 0;//cueBall.speed *  cueBall.dx;//(cueBall.dx/ Math.sqrt(cueBall.dx*cueBall.dx + cueBall.dy*cueBall.dy));
      cueBall.vy = 0;//cueBall
      status.currentStatus = 6;
    }

  }
}



