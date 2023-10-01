package sim;

import java.util.HashMap;
import java.util.Map;

/**
 * StatusMessage represents the status of the simulation,
 *        providing mappings of status codes to their corresponding messages.
 * It tracks the current status of the simulation and provides a default initialization value.
 */
public class StatusMessage {
  protected int currentStatus;

  /**
   * A map containing mappings of status codes to their corresponding messages.
   */
  Map<Integer, String> statusStrings = new HashMap<Integer, String>() {
    {
      put( 0, "Ball not set up");
      put( 1, "Simulation started");
      put( 2, "Ball hit left edge");
      put( 3, "Ball hit right edge");
      put( 4, "Ball hit top edge");
      put( 5, "Ball hit bottom edge");
      put( 6, "Ball is stationary");
    }
  };

  /**
   * Constructs a StatusMessage and initializes the current status to a default value.
   */
  public StatusMessage() {
    // Initialize currentStatus to a default value
    this.currentStatus = 0;
  }

}
