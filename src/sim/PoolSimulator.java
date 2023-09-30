package sim;

/**
 * This still needs to be edited
 */
public interface PoolSimulator {
  void start(int x,int y,int radius,int speed,double dx,double dy) throws IllegalArgumentException;

  void advance();

  int getTableWidth();

  int getTableHeight();

  double getBallPositionX();

  double getBallPositionY();

  double getBallRadius();

  double getBallVelocityX();

  double getBallVelocityY();
  String getStatus();
}
