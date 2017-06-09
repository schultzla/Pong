import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Logan on 6/9/2017.
 */
public class Pong extends Applet implements Runnable, KeyListener {
    private static final int WIDTH = 700, HEIGHT = 500;
    private Thread thread;
    private HumanPaddle humanPlayer;
    private ComputerPaddle computerPlayer;
    private Ball ball;
    private static Integer userScore = 0, compScore = 0;
    private static boolean userWon;

    public void init() {
        setSize(WIDTH, HEIGHT);
        addKeyListener(this);
        setVisible(true);
        ball = new Ball();
        userWon = false;
        computerPlayer = new ComputerPaddle(2, ball);
        humanPlayer = new HumanPaddle(1);
        thread = new Thread(this);
        thread.start();
    }

    public void update(Graphics g) {
        paint(g);
    }


    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (ball.getX() < 20) {
            g.setColor(Color.black);
            compScore++;
            userWon = false;
            ball.reset();
        } else if (ball.getX() > 660) {
            userScore++;
            userWon = true;
            ball.reset();
        } else {
            humanPlayer.draw(g);
            ball.draw(g);
            computerPlayer.draw(g);
            g.setColor(Color.black);
            g.drawLine(WIDTH/2, 0, WIDTH/2, 500);
            g.drawString(userScore.toString(), WIDTH/2 - 50, 250);
            g.drawString(compScore.toString(), WIDTH/2 + 50, 250);
        }
    }

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


    public void keyTyped(KeyEvent e) {

    }


    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            humanPlayer.setUpAccel(true);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            humanPlayer.setDownAccel(true);
        }
    }


    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_UP) {
            humanPlayer.setUpAccel(false);
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            humanPlayer.setDownAccel(false);
        }
    }

    public static int getFrameHeight() {
        return HEIGHT;
    }

    public static int getFrameWidth() {
        return WIDTH;
    }

    public static boolean getDecision() {
        return userWon;
    }
}
