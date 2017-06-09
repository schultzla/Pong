import java.awt.*;

/**
 * Created by Logan on 6/9/2017.
 */
public class HumanPaddle implements Paddle {
    double y, yVel;
    private boolean upAccel, downAccel;
    int player, x;
    private final double FRICTION = 0.94;
    private final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 80;

    public HumanPaddle(int player) {
        upAccel = false; downAccel = false;
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


    public void move() {
        if(upAccel) {
            yVel -= 2;
        } else if(downAccel) {
            yVel += 2;
        } else if (!upAccel && !downAccel) {
            yVel *= FRICTION;
        }
        if(yVel >= 5) {
            yVel = 5;
        } else if (yVel <= -5) {
            yVel = -5;
        }
        y += yVel;

        if(y < 0) {
            y = 0;
        } else if (y > Pong.getFrameHeight() - PADDLE_HEIGHT) {
            y = Pong.getFrameHeight() - PADDLE_HEIGHT;
        }
    }

    public void setUpAccel(boolean input) {
        upAccel = input;
    }

    public void setDownAccel(boolean input) {
        downAccel = input;
    }


    public int getY() {
        return (int)y;
    }
}
