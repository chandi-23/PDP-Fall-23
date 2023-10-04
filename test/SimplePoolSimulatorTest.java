import org.junit.Test;
import sim.PoolSimulator;
import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;

/**
 * This class contains unit tests for the SimplePoolSimulator class.
 * It tests various functionalities of the SimplePoolSimulator
 *        to ensure its correctness and expected behavior.
 */
public class SimplePoolSimulatorTest {

  @Test
  public void testValidSimpleTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 15, "simple");
    assertEquals(10, pool.getTableWidth());
    assertEquals(15, pool.getTableHeight());
    assertEquals("Status: Ball not set up", pool.getStatus());
  }

  @Test
  public void testValidFrictionTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 15, "friction");
    assertEquals(10, pool.getTableWidth());
    assertEquals(15, pool.getTableHeight());
    assertEquals("Status: Ball not set up", pool.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidthTable() {
    PoolSimulator pool = new SimplePoolSimulator(-10, 10, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeightTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroHeightTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 0, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testZeroWidthTable() {
    PoolSimulator pool = new SimplePoolSimulator(0, 10, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTypeTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "Sample");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroTable() {
    PoolSimulator pool = new SimplePoolSimulator(0, 0, "Simple");
  }

  @Test
  public void testValidBallSimpleCreation() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(4, 5, 1, 5, -3, -2);
    assertEquals(4, pool.getBallPositionX(), 0.01);
    assertEquals(5, pool.getBallPositionY(), 0.01);
    assertEquals(1, pool.getBallRadius(), 0.01);
    assertEquals(-4.160, pool.getBallVelocityX(), 0.01);
    assertEquals(-2.7735, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Simulation started", pool.getStatus());
  }

  @Test
  public void testValidBallFrictionCreation() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.start(4, 5, 1, 5, -3, -2);
    assertEquals(4, pool.getBallPositionX(), 0.01);
    assertEquals(5, pool.getBallPositionY(), 0.01);
    assertEquals(1, pool.getBallRadius(), 0.01);
    assertEquals(-4.160, pool.getBallVelocityX(), 0.01);
    assertEquals(-2.7735, pool.getBallVelocityY(), 0.01);
    assertEquals("Status: Simulation started", pool.getStatus());
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallOutTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(14, 5, 1, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinRadiusX() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(4, 5, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinRadiusY() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(5, 4, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinRadius() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(4, 4, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinFrictionRadiusX() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.start(4, 5, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinFrictionRadiusY() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.start(5, 4, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallWithinFrictionRadius() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.start(4, 4, 5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallOutTable2() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(-14, 5, 1, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroRadiusTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "simple");
    pool.start(4, 5, 0, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroRadiusFrictionTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "friction");
    pool.start(4, 5, 0, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeRadiusTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "simple");
    pool.start(4, 5, -5, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeSpeedTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(4, 5, 10, -5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeSpeedFrictionTable() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.start(4, 5, 10, -5, -3, -2);
  }


  @Test
  public void testSimpleStart() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");

    pool.start(4, 5, 1, 16, 1, -2);
    for (int i = 1; i <= 3; i++) {
      pool.advance();
    }

    assertEquals(8, pool.getBallPositionX(), 0.01);
    assertEquals(9, pool.getBallPositionY(), 0.01);
    assertEquals(-0.447, pool.getBallVelocityX(), 0.01);
    assertEquals(-0.89, pool.getBallVelocityY(), 0.01);
    assertEquals(1, pool.getBallRadius(), 0.01);

  }

  @Test
  public void testRandomFriction() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");

    pool.start(4, 5, 1, 5, 1, -2);
    for (int i = 1; i <= 2; i++) {
      pool.advance();
    }

    assertEquals(9, pool.getBallPositionX(), 0.01);
    assertEquals(7, pool.getBallPositionY(), 0.01);
    assertEquals(-0.782, pool.getBallVelocityX(), 0.01);
    assertEquals(1.5656, pool.getBallVelocityY(), 0.01);
    assertEquals(1, pool.getBallRadius(), 0.01);

  }

  @Test
  public void testStartWOBallXYR() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionX(), 0.01);
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallPositionY(), 0.01);
    assertEquals(Double.NEGATIVE_INFINITY, pool.getBallRadius(), 0.01);
  }

  @Test(expected = IllegalStateException.class)
  public void testAdvanceWOBallXYR() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    pool.advance();
  }

  @Test
  public void testSimpleBallMovementRandomLastX() {

    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, 0.410000,0.160000);
    for (int i = 1; i <= 38; i++) {
      pool.advance();
    }
    assertEquals(281.7500000000015, pool.getBallPositionX(),0.01);
  }

  @Test
  public void testSimpleBallMovementRandomLastVx() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, 0.410000,0.160000);
    for (int i = 1; i <= 37; i++) {
      pool.advance();
    }
    assertEquals(-2.7947328584551805, pool.getBallVelocityX(),0.01);
  }

  @Test
  public void testSimpleBallMovementRandomStationaryVx() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, 0.410000,0.160000);
    for (int i = 1; i <= 38; i++) {
      pool.advance();
    }
    assertEquals(0, pool.getBallVelocityX(),0.01);
  }

  @Test
  public void testFrictionAfterFirst() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(90,175,9,188, 0.410000,0.160000);
    pool.advance();

    assertEquals(-173.55884211845742, pool.getBallVelocityX(),0.01);
  }

  @Test
  public void testFrictionPos() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(90,175,9,188, 0.410000,0.160000);

    for (int i = 1; i <= 62; i++) {
      pool.advance();
    }
    assertEquals(63.69183643219577, pool.getBallPositionX(),0.01);
  }

  @Test
  public void testFrictionBeyondStationary() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(90,175,9,188, 0.410000,0.160000);

    for (int i = 1; i <= 108; i++) {
      pool.advance();
    }
    assertEquals(63.69183643219577, pool.getBallPositionX(),0.01);
    assertEquals(0, pool.getBallVelocityX(),0.01);
    assertEquals(0, pool.getBallVelocityY(),0.01);
  }

  @Test
  public void testFrictionHorizontal() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(90,175,9,188, -2,0);
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  @Test
  public void testFrictionZeroSpeedDirection() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(91,173,9,188, 0,0);

    for (int i = 1; i <= 108; i++) {
      pool.advance();
    }

    assertEquals(91.0, pool.getBallPositionX(),0.01);
    assertEquals(173.0, pool.getBallPositionY(),0.01);
  }

  @Test
  public void testFrictionZeroSpeed() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"friction");
    pool.start(90,175,9,0, 0,0);

    for (int i = 1; i <= 3; i++) {
      pool.advance();
    }

    assertEquals(90.0, pool.getBallPositionX(),0.01);
    assertEquals(175.0, pool.getBallPositionY(),0.01);
  }

  @Test
  public void testSimpleZeroSpeedDirection() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, 0,0);

    for (int i = 1; i <= 3; i++) {
      pool.advance();
    }

    assertEquals(90.0, pool.getBallPositionX(),0.01);
    assertEquals(175.0, pool.getBallPositionY(),0.01);
  }

  @Test
  public void testSimpleZeroSpeed() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,0, 0,0);

    for (int i = 1; i <= 3; i++) {
      pool.advance();
    }

    assertEquals(90.0, pool.getBallPositionX(),0.01);
    assertEquals(175.0, pool.getBallPositionY(),0.01);
  }

  @Test
  public void testSimpleHorizontal() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, -2,0);
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit left edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit right edge", pool.getStatus());
  }

  @Test
  public void testSimpleVertical() {
    SimplePoolSimulator pool = new SimplePoolSimulator(400,400,"simple");
    pool.start(90,175,9,188, 0,-1);
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit bottom edge", pool.getStatus());
    pool.advance();
    assertEquals("Status: Ball hit top edge", pool.getStatus());
  }

}