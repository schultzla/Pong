import java.awt.*;

/**
 * Created by Logan on 6/9/2017.
 */
public class Ball {
    private static double xVel, yVel, x, y;

    public Ball() {
        x = 350;
        y = 250;
        xVel = -2;
        yVel = 1;
    }

    public void checkCollision(Paddle p1, Paddle p2) {

    }

    public void move() {
        x += xVel;
        y += yVel;

        if(y < 10) {
            yVel = -yVel;
        } else if (y > Pong.getFrameHeight() - 10) {
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
