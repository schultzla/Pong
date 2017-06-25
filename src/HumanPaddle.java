import java.awt.*;

/**
 * Created by Logan on 6/9/2017.
 *
 * Object for the paddle that is controlled by the player. Main methods are the constructor, draw, and move methods.
 */
public class HumanPaddle implements Paddle {
    private double y, yVel;
    private boolean upAccel, downAccel;
    private final int x;
    private final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 80;

    /**
     * Constructor, takes in an int which is the player number. The player number decides which side the paddle is
     * on
     * @param player designates 1 or 2, 1 being human paddle, 2 being computer paddle
     */
    public HumanPaddle(int player) {
        upAccel = false; downAccel = false;
        y = 210; yVel = 0;
        if(player == 1) {
            x = 20;
        } else {
            x = 660;
        }
    }

    /**
     * Draws the paddle
     * @param g graphics mechanic
     */
    public void draw(Graphics g) {
        g.setColor(Color.black);
        g.fillRect(x, (int) y, PADDLE_WIDTH, PADDLE_HEIGHT);
    }


    /**
     * Does all of the movement using the upAccel/downAccel booleans. If the up key is pressed, upAccel is set to
     * true, and the yVel has 2 removed from it, then it is added to the y value which is the paddle location. There
     * are boundaries set to make sure the paddle can never get too fast and can't go off the board
     */
    public void move() {
        if(upAccel) {
            yVel -= 2;
        } else if(downAccel) {
            yVel += 2;
        } else {
            //If neither is being pressed, the paddle slows down in a gliding manner
            double FRICTION = 0.94;
            yVel *= FRICTION;
        }
        if(yVel >= 2) {
            yVel = 2;
        } else if (yVel <= -2) {
            yVel = -2;
        }
        y += yVel;

        if(y < 0) {
            y = 0;
        } else if (y > PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30) {
            y = PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30;
        }
    }

    /**
     * Setter for the upAccel boolean
     * @param input sets true if paddle should move up
     */
    public void setUpAccel(boolean input) {
        upAccel = input;
    }

    /**
     * Setter for the downAccel boolean
     * @param input sets true if paddle should move down
     */
    public void setDownAccel(boolean input) {
        downAccel = input;
    }

    /**
     * Getter for the current y position of the paddle
     * @return the y position of paddle
     */
    public int getY() {
        return (int)y;
    }

    /**
     * Getter for the current x position f the paddle
     * @return the x position of paddle
     */
    public int getX() { return x; }

    /**
     * Getter for the width
     * @return the paddle width
     */
    public int getPdlWidth() { return PADDLE_WIDTH; }

    /**
     * Getter for the height
     * @return the paddle height
     */
    public int getPdlHeight() { return PADDLE_HEIGHT; }
}
