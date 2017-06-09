import java.awt.*;
import java.util.Random;

/**
 * Created by Logan on 6/9/2017.
 */
public class Ball {
    private static double xVel, yVel, x, y;
    Random r = new Random();

    public Ball() {
        x = 350;
        y = 250;
        xVel = -2.5;
        yVel = 1;
    }

    public void reset() {
        x = 350;
        y = 250;
        yVel = r.nextInt(3 ) + 1;
        if(PongPanel.getDecision()) {
            xVel = 2.5;
        } else {
            xVel = -2.5;
        }
    }

    public void checkCollision(Paddle p1, Paddle p2) {
        /*
        Add in a 2 point difference to prevent cutoff
         */
        if(x <= 45) {
            if(y >= p1.getY() - 2 && y <= p1.getY() + 82) {
                xVel = -xVel;
            }
        } else if (x >= 655) {
            if(y >= p2.getY() - 2 && y <= p2.getY() + 82) {
                xVel = -xVel;
            }
        }
    }

    public void move() {
        x += xVel;
        y += yVel;

        if(y < 10) {
            yVel = -yVel;
        } else if (y > PongPanel.getFrameHeight() - 40) {
            yVel = -yVel;
        }
    }

    public void draw(Graphics g) {
        g.setColor(Color.black);
        //subtract 10 so circle's center is 10, 10 rather than 0, 0
        g.fillOval((int)x - 10, (int)y - 10, 20, 20);
    }

    public int getX() {
        return (int)x;
    }

    public int getY() {
        return (int)y;
    }
}
