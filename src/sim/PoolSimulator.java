package sim;

/**
 * SimplePoolSimulator represents a simple pool game simulator that
 * implements the PoolSimulator interface.
 * It provides functionalities to start the game, advance the simulation,
 * and retrieve information about the table and cue ball.
 */
public interface PoolSimulator {

  /**
   * Starts the simulation with specified parameters for the cue ball.
   *
   * @param x      The x-coordinate of the cue ball's initial position.
   * @param y      The y-coordinate of the cue ball's initial position.
   * @param radius The radius of the cue ball.
   * @param speed  The initial speed of the cue ball.
   * @param dx     The x-component of the cue ball's initial velocity direction.
   * @param dy     The y-component of the cue ball's initial velocity direction.
   * @throws IllegalArgumentException if any of the input parameters are invalid.
   */
  void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException;

  /**
   * Advances the simulation by one step, updating the cue ball's position and velocity.
   *
   * @throws IllegalStateException if the cue ball has not been placed yet.
   */
  void advance();

  /**
   * Gets the width of the pool table.
   *
   * @return The width of the pool table.
   */
  int getTableWidth();

  /**
   * Gets the height of the pool table.
   *
   * @return The height of the pool table.
   */
  int getTableHeight();

  /**
   * Gets the x-coordinate of the cue ball's current position.
   *
   * @return The x-coordinate of the cue ball's position.
   */
  double getBallPositionX();

  /**
   * Gets the y-coordinate of the cue ball's current position.
   *
   * @return The y-coordinate of the cue ball's position.
   */
  double getBallPositionY();

  /**
   * Gets the radius of the cue ball.
   *
   * @return The radius of the cue ball.
   */
  double getBallRadius();

  /**
   * Gets the x-component of the cue ball's velocity.
   *
   * @return The x-component of the cue ball's velocity.
   */
  double getBallVelocityX();

  /**
   * Gets the y-component of the cue ball's velocity.
   *
   * @return The y-component of the cue ball's velocity.
   */
  double getBallVelocityY();

  /**
   * Gets the current status of the simulation.
   *
   * @return The current status of the simulation as a descriptive string.
   */
  String getStatus();
}
