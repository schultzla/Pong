import java.awt.*;
import java.util.Random;

/**
 * Created by Logan on 6/9/2017.
 */
public class ComputerPaddle implements Paddle {
    Random r = new Random();
    private double y, yVel;
    private boolean upAccel, downAccel;
    private int player, x;
    private final double FRICTION = 0.94;
    private final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 80;
    private Ball ball;

    public ComputerPaddle(int player, Ball b) {
        upAccel = false; downAccel = false;
        ball = b;
        y = 210; yVel = 0;
        if(player == 1) {
            x = 20;
        } else {
            x = 660;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, (int) y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }


    public static void log(int i) {
        System.out.println(i);
    }

    public void move() {
        int dir = ball.getY() - (int)y;
        if(dir < 0) {
            y -= .95;
        } else {
            y += .95;
        }

        if(y < 0) {
            y = 0;
        } else if (y > PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30) {
            y = PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30;
        }
    }

    public int getY() {
        return (int)y;
    }
}
