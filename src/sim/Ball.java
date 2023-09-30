package sim;

public class Ball {
  public double x;
  public double y;
  public double radius;
  public double speed;
  public double dx;
  public double dy;

  protected double vx;
  protected double vy;
  public Ball(double x, double y, double radius, double speed, double dx, double dy) {
    this.x = x;
    this.y = y;
    this.radius = radius;
    this.speed = speed;
    this.dx = (dx/ Math.sqrt(dx*dx + dy*dy));
    this.dy = (dy/ Math.sqrt(dx*dx + dy*dy));
    this.vx = speed * this.dx;
    this.vy = speed * this.dy;

  }

}
