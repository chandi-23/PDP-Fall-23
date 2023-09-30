package sim;

import java.util.HashMap;
import java.util.Map;

public class statusMessage {
  protected int currentStatus;
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

  public statusMessage() {
    // Initialize currentStatus to a default value, e.g., 0
    this.currentStatus = 0;
  }

}
