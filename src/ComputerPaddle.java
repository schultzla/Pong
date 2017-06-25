import java.awt.*;
import java.util.Random;

/**
 * Created by Logan on 6/9/2017.
 *
 * Object that represents the computer paddle. Implements an AI to predict where the ball is going. However, it would
 * be unbeatable if it could move fast enough so it lags behind slightly to give the human a chance
 */
public class ComputerPaddle implements Paddle {
    Random r = new Random();
    private double y;
    private final int x;
    private final int PADDLE_WIDTH = 20, PADDLE_HEIGHT = 80;
    private final Ball ball;

    /**
     * Constructor. Takes in a ball so the paddle can track its position
     * @param player int which represents 1 or 2 if they are human or computer
     * @param b ball that the computer needs to track
     */
    public ComputerPaddle(int player, Ball b) {
        boolean upAccel = false;
        boolean downAccel = false;
        ball = b;
        y = 210;
        double yVel = 0;
        if (player == 1) {
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
     * Handles the movements. Rather than moving the y position by 1 every time when following the ball (which would
     * lead the paddle to save the ball every time), it goes by .95 so it has a chance to miss
     */
    public void move() {
        int dir = ball.getY() - (int) y;
        if (dir < 0) {
            y -= .95;
        } else {
            y += .95;
        }

        if (y < 0) {
            y = 0;
        } else if (y > PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30) {
            y = PongPanel.getFrameHeight() - PADDLE_HEIGHT - 30;
        }
    }

    /**
     * Getter for the y position
     * @return the y position
     */
    public int getY() {
        return (int) y;
    }

    /**
     * Getter for the x position
     * @return the x position
     */
    public int getX() {
        return x;
    }

    /**
     * Getter for the width
     * @return the paddle width
     */
    public int getPdlWidth() {
        return PADDLE_WIDTH;
    }

    /**
     * Getter for the height
     * @return the paddle height
     */
    public int getPdlHeight() {
        return PADDLE_HEIGHT;
    }
}
