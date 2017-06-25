import java.awt.*;
import java.util.Random;

/**
 * Created by Logan on 6/9/2017.
 *
 * The ball object, holds the information about the ball and methods for it to check physics
 */
class Ball {
    private static double xVel, yVel, x, y;
    Random r = new Random();

    /**
     * Constructor, instantiates the starting position of the ball and it's starting velocity
     */
    public Ball() {
        x = 350;
        y = 250;
        xVel = -2.5;
        yVel = 1;
    }

    /**
     * Has the user who scored a point serve the ball. Ball's starting point is the user's paddle
     */
    public void reset(Paddle winner) {
        if(winner.getX() == 20) {
            PongPanel.setUserScore();
            xVel = 2.5;
            x = winner.getX() + winner.getPdlWidth() + 10;
        } else {
            PongPanel.setCompScore();
            xVel = -2.5;
            x = winner.getX() - winner.getPdlWidth() - 10;
        }
        y = winner.getY() + winner.getPdlHeight()/2;
        yVel = 1;
    }

    /**
     * Checks collision of the balls and the two paddles
     * @param p1 User paddle
     * @param p2 Computer paddle
     */
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

    /**
     * Handles the movement of the ball by adding velocity to the position
     */
    public void move() {
        x += xVel;
        y += yVel;

        if(y < 10) {
            yVel = -yVel;
        } else if (y > PongPanel.getFrameHeight() - 40) {
            yVel = -yVel;
        }
    }

    /**
     * Draws the ball
     * @param g graphics mechanic
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        //subtract 10 so circle's center is 10, 10 rather than 0, 0
        g.fillOval((int)x - 10, (int)y - 10, 20, 20);
    }

    /**
     * Getter for the x position
     * @return the x position
     */
    public int getX() {
        return (int)x;
    }

    /**
     * Getter for the y position
     * @return the y position
     */
    public int getY() {
        return (int)y;
    }
}
