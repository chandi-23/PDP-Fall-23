package sim;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.List;
import java.util.ArrayList;

public class BallView extends JFrame {

    List<Integer> ballX,ballY,ballRadius;
    int tableWidth,tableHeight;
    int step;
    public BallView(PoolSimulator simulator) {
        super("Ball Viewer");
        extractBallPositions(simulator);
        step = 1;
        //setSize(500,500);
        this.add(new JPanel() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension((int)(1.5*tableWidth),(int)(1.5*tableHeight));
            }
           @Override
           protected void paintComponent(Graphics g) {

               super.paintComponent(g);
               int offset = 20;
               Graphics2D g2d = (Graphics2D) g;
               int height = this.getPreferredSize().height;
               g2d.setColor(Color.BLACK);
               g2d.drawRect(offset,offset,tableWidth,tableHeight);
               //draw the different ball positions
               for (int i=step-1;i<=step;i+=1) {
                   g2d.drawOval(
                           offset+(int)ballX.get(i).doubleValue()-(int)ballRadius.get(i).doubleValue()
                           ,offset+tableHeight-1-((int)ballY.get(i).doubleValue()+(int)ballRadius.get(i).doubleValue())
                           ,2*(int)ballRadius.get(i).doubleValue()
                           ,2*(int)ballRadius.get(i).doubleValue());
               }

               //draw lines between the ball positions
               for (int i=step-1;i<step;i+=1) {
                   g2d.drawLine(
                           offset+(int)ballX.get(i).doubleValue()
                           ,offset+tableHeight-1-(int)ballY.get(i).doubleValue()
                           ,offset+(int)ballX.get(i+1).doubleValue()
                           ,offset+tableHeight-1-(int)ballY.get(i+1).doubleValue());
               }
           }
        });
        this.setFocusable(true);
        this.requestFocus();
        this.addKeyListener(new KeyAdapter() {
            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_LEFT) {
                    step -=1;
                    if (step<1) {
                        step = 1;
                    }
                    repaint();
                }
                else if (e.getKeyCode()==KeyEvent.VK_RIGHT) {
                    step+=1;
                    if (step>=ballX.size()) {
                        step = ballX.size()-1;
                    }
                    repaint();
                }
            }
        });
        pack();
        setVisible(true);
    }

    public static void main(String []args) {
        PoolSimulator simulator = new SimplePoolSimulator(400,400,"friction");
        simulator.start(100,100,20,60,1.1,-2);
        JFrame view = new BallView(simulator);
    }
    private void extractBallPositions(PoolSimulator simulator) {
        ballX = new ArrayList<Integer>();
        ballY = new ArrayList<Integer>();
        ballRadius = new ArrayList<Integer>();

        tableWidth = simulator.getTableWidth();
        tableHeight = simulator.getTableHeight();
        while (!simulator.getStatus().equals("Status: Ball is stationary")) {
            ballX.add((int) simulator.getBallPositionX());
            ballY.add((int) simulator.getBallPositionY());
            ballRadius.add((int) simulator.getBallRadius());
            System.out.println(simulator.getStatus());
            simulator.advance();
        }
        //the final resting place of the ball
        ballX.add((int) simulator.getBallPositionX());
        ballY.add((int) simulator.getBallPositionY());
        ballRadius.add((int) simulator.getBallRadius());
    }


}
