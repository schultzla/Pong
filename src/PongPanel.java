import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Logan on 6/9/2017.
 *
 * Where the program runs. The panel is then added to the frame in Pong.java to run. Here, the primary methods are
 * the constructor, the paint method, and the run method.
 */
class PongPanel extends JPanel implements  Runnable, KeyListener {
    private static final int WIDTH = 700, HEIGHT = 500;
    private final HumanPaddle humanPlayer;
    private final ComputerPaddle computerPlayer;
    private final Ball ball;
    private static Integer userScore = 0, compScore = 0;
    private static boolean userWon;

    /**
     * Constructor, instantiates the ball, two paddles, and starts the thread
     */
    public PongPanel() {
        ball = new Ball();
        userWon = false;
        computerPlayer = new ComputerPaddle(2, ball);
        humanPlayer = new HumanPaddle(1);
        addKeyListener(this);
        setFocusable(true);
        Thread thread = new Thread(this);
        thread.start();
    }

    /**
     * Update method for drawing
     * @param g graphics mechanic
     */
    public void update(Graphics g) {
        paint(g);
    }


    /**
     * Paints the items onto the panel. If the ball goes past either paddle, it adds a point and sets the scorer to
     * serve. Otherwise, just draws everything else as normal
     * @param g graphics mechanic
     */
    public void paintComponent(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (ball.getX() < 20) {
            ball.reset(computerPlayer);
        } else if (ball.getX() > 660) {
            ball.reset(humanPlayer);
        } else {
            humanPlayer.draw(g);
            ball.draw(g);
            computerPlayer.draw(g);
            g.drawLine(WIDTH/2, 0, WIDTH/2, 500);
            g.drawString(userScore.toString(), WIDTH/2 - 50, 250);
            g.drawString(compScore.toString(), WIDTH/2 + 50, 250);
        }
    }

    /**
     * Controls all of the movements, just calls the individual objects move methods and repaints
     */
    public void run() {
        while(true) {
            humanPlayer.move();
            computerPlayer.move();
            ball.move();
            ball.checkCollision(humanPlayer, computerPlayer);

            repaint();
            try {
                Thread.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


    public void keyTyped(KeyEvent e) {}

    /**
     * Sets up the controls for up and down, setting if they are pressed to enable movement
     * @param e the key event
     */
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            humanPlayer.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            humanPlayer.setDownAccel(true);
        }
    }


    /**
     * Sets up the release methods, if the buttons are released then the movement is stopped
     * @param e the key event
     */
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            humanPlayer.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            humanPlayer.setDownAccel(false);
        }
    }

    /**
     * Setter for the userScore
     */
    public static void setUserScore() {
        userScore += 1;
    }

    /**
     * Setter for the compScore
     */
    public static void setCompScore() {
        compScore += 1;
    }

    /**
     * Getter for frame height
     * @return The height of the frame
     */
    public static int getFrameHeight() {
        return HEIGHT;
    }

    /**
     * Getter for the frame wdith
     * @return The width of the frame
     */
    public static int getFrameWidth() {
        return WIDTH;
    }

    /**
     * Getter for the decision of who won to decide who serves
     * @return Which padddle won
     */
    public static boolean getDecision() {
        return userWon;
    }
}
