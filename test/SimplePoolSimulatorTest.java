import org.junit.Test;
import sim.PoolSimulator;
import sim.SimplePoolSimulator;

import static org.junit.Assert.assertEquals;

public class SimplePoolSimulatorTest {

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidWidthTable(){
    PoolSimulator pool = new SimplePoolSimulator(-10, 10, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidHeightTable(){
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "simple");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidTypeTable(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "Sample");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroTable(){
    PoolSimulator pool = new SimplePoolSimulator(0, 0, "Sample");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallOutTable(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(14, 5, 1, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidBallOutTable2(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(-14, 5, 1, 5, -3, -2);
  }
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeRadiusTable(){
    PoolSimulator pool = new SimplePoolSimulator(10, -10, "simple");
    pool.start(4, 5, 0, 5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidNegativeSpeedTable(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");
    pool.start(4, 5, 10, -5, -3, -2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void testInvalidZeroSpeed(){
    PoolSimulator pool = new SimplePoolSimulator(0, 0, "Sample");
    pool.start(4, 5, 10, 0, -3, -2);
  }

  @Test
  public void testStart(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "simple");

    pool.start(4, 5, 1, 16, 1, -2);

    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());

  }

  @Test
  public void testNStart(){
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");

    pool.start(4, 5, 1, 5, 1, -2);

    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
    System.out.println("*************************");
    pool.advance();
    System.out.println(pool.getStatus() + " x: " + pool.getBallPositionX() +" y: "+ pool.getBallPositionY());
    System.out.println("Speed x:" + pool.getBallVelocityX() + " y: " + pool.getBallVelocityY());
  }

  @Test
  public void testStartWOBall() {
    PoolSimulator pool = new SimplePoolSimulator(10, 10, "friction");
    System.out.println(pool.getBallPositionX());
    System.out.println(pool.getStatus());

    //pool.start(4, 5, 1, 5, 1, -2);
    }

  @Test
  public void test2(){

    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"simple");
    si.start(90,175,9,188, 0.410000,0.160000);
    //si.advance();
    //Scenario: Starting position:(90,175), radius:9, speed:188, velocity:(0.410000,0.160000) after advancing 38 times
    //X position of ball is not as expected expected:<281.7500000000015> but was:<391.0>
    for(int i =1; i<=38; i++){
      si.advance();
      System.out.println("for the ith:" +i +" "+si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    assertEquals(281.7500000000015, si.getBallPositionX(),0.1);
    //assertEquals(-170.47870436576602, si.getBallVelocityX(),0.1);
  }

  @Test
  public void test23(){

    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"simple");
    si.start(90,175,9,188, 0.410000,0.160000);
    //si.advance();
    //Scenario: Starting position:(90,175), radius:9, speed:188, velocity:(0.410000,0.160000) after advancing 38 times
    //X position of ball is not as expected expected:<281.7500000000015> but was:<391.0>
    for(int i =1; i<=37; i++){
      si.advance();
      System.out.println("for the ith:" +i +" "+si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    assertEquals(-2.7947328584551805, si.getBallVelocityX(),0.1);
    //assertEquals(-170.47870436576602, si.getBallVelocityX(),0.1);
  }
  @Test
  public void test24(){

    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"simple");
    si.start(90,175,9,188, 0.410000,0.160000);
    //si.advance();
    //Scenario: Starting position:(90,175), radius:9, speed:188, velocity:(0.410000,0.160000) after advancing 38 times
    //X position of ball is not as expected expected:<281.7500000000015> but was:<391.0>
    for(int i =1; i<=38; i++){
      si.advance();
      System.out.println("for the ith:" +i +" " + si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    assertEquals(0, si.getBallVelocityX(),0.1);
    //assertEquals(-170.47870436576602, si.getBallVelocityX(),0.1);
  }


  @Test
  public void testFriction1(){
    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"friction");
    si.start(90,175,9,188, 0.410000,0.160000);
    si.advance();
    System.out.println(si.getBallPositionX());
    System.out.println(si.getBallPositionY());
    assertEquals(-173.55884211845742, si.getBallVelocityX(),0.1);

  }

  @Test
  public void testFriction2(){
    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"friction");
    System.out.println("for the ith:"  +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
    si.start(90,175,9,188, 0.410000,0.160000);
    System.out.println("for the ith:"  +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
    for(int i =1; i<=62; i++){
      si.advance();
      System.out.println("for the ith:" +i +" " + si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    System.out.println(si.getBallPositionX());
    System.out.println(si.getBallPositionY());
    assertEquals(63.69183643219577, si.getBallPositionX(),0.1);
  }

  @Test
  public void testFriction3(){
    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"friction");
    System.out.println("for the ith:"  +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
    si.start(90,175,9,188, 0.410000,0.160000);
    si.advance();
    System.out.println("for the ith:"  +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());

    for(int i =1; i<=108; i++){
      si.advance();
      System.out.println("for the ith:" +i +" " + si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    System.out.println(si.getBallPositionX());
    System.out.println(si.getBallPositionY());
    assertEquals(63.69183643219577, si.getBallPositionX(),0.1);
  }

  @Test
  public void testFrictionHorizontal(){
    SimplePoolSimulator si= new SimplePoolSimulator(400,400,"friction");
    si.start(90,175,9,188, 1,0);
    for(int i =1; i<=108; i++){
      si.advance();
      System.out.println("for the ith:" +i +" " + si.getStatus());
      System.out.println("for the ith:" +i +" x:"+si.getBallPositionX() + " y:"+ si.getBallPositionY());
      System.out.println("for the ith:" +i +" vx:"+si.getBallVelocityX() + " vy:"+ si.getBallVelocityY());
    }
    System.out.println(si.getBallPositionX());
    System.out.println(si.getBallPositionY());
    //assertEquals(63.69183643219577, si.getBallPositionX(),0.1);
  }
}