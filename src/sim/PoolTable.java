package sim;

import java.util.HashMap;
import java.util.Map;

public abstract class PoolTable {
  protected int tableWidth;
  protected int tableHeight;
  protected String tableType;
  protected Map<String, Double> distances = new HashMap<>();

  statusMessage status = new statusMessage();

  public PoolTable(int width, int height, String type, statusMessage status){
    this.tableWidth = width;
    this.tableHeight = height;
    this.tableType = type;
    this.status = status;
  }

  protected abstract void move (Ball cueBall);
}
