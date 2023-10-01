package sim;

/**
 * Represents a ball in a simulation, with properties
 * such as position, radius, speed, and direction.
 */
public class Ball {

  public double x;          // x-coordinate of the ball's center
  public double y;          // y-coordinate of the ball's center
  public double radius;     // radius of the ball
  public double speed;      // speed of the ball
  public double dx;         // x-component of the direction vector
  public double dy;         // y-component of the direction vector

  protected double vx;     // velocity in the x-direction
  protected double vy;     // velocity in the y-direction


  /**
   * Constructor to create a Ball object with specified parameters.
   *
   * @param x      The initial x-coordinate of the ball's center.
   * @param y      The initial y-coordinate of the ball's center.
   * @param radius The radius of the ball.
   * @param speed  The initial speed of the ball.
   * @param dx     The x-component of the normalized direction vector.
   * @param dy     The y-component of the normalized direction vector.
   */
  public Ball(double x, double y, double radius, double speed, double dx, double dy) {

    this.x = x;
    this.y = y;
    this.radius = radius;
    this.speed = speed;
    // If speed is 0, set vx and vy to 0
    if (speed == 0) {
      this.vx = 0;
      this.vy = 0;
    } else {
      // Calculate normalized direction vector (dx, dy)
      double magnitude = Math.sqrt(dx * dx + dy * dy);
      this.dx = (magnitude == 0) ? 0 : dx / magnitude;
      this.dy = (magnitude == 0) ? 0 : dy / magnitude;

      // Set vx and vy based on normalized direction vector and speed
      this.vx = speed * this.dx;
      this.vy = speed * this.dy;
    }
  }

}
