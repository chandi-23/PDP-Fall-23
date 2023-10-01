package sim;

import java.util.HashMap;
import java.util.Map;

/**
 * PoolTable is an abstract class representing a pool table in a simulation.
 * It contains essential properties such as table width, height,
 *      type, and a duration map to store time durations.
 * Subclasses must implement the move method to handle
 *        cue ball movement based on specific table types.
 */
public abstract class PoolTable {
  protected int tableWidth;
  protected int tableHeight;
  protected String tableType;

  // A map to store durations related to different table edges.
  protected Map<String, Double> duration = new HashMap<>();

  // The status message object to track the simulation status.
  StatusMessage status;

  /**
   * Constructs a PoolTable with the specified dimensions and type of table.
   *
   * @param width   The width of the table.
   * @param height  The height of the table.
   * @param type    The type of table.
   * @param status  The status message object to track the simulation status.
   */
  public PoolTable(int width, int height, String type, StatusMessage status) {
    this.tableWidth = width;
    this.tableHeight = height;
    this.tableType = type;
    this.status = status;
  }

  /**
   * Moves the cue ball based on specific table type's physics.
   *
   * @param cueBall The cue ball object.
   */
  protected abstract void move(Ball cueBall);
}
