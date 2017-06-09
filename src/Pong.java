import javax.swing.*;
import java.applet.Applet;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Created by Logan on 6/9/2017.
 */
public class Pong extends Applet implements Runnable, KeyListener {
    private static final int WIDTH = 700, HEIGHT = 500;
    Thread thread;
    HumanPaddle humanPlayer;
    Ball ball;

    public void init() {
        setSize(WIDTH, HEIGHT);
        addKeyListener(this);
        setVisible(true);
        humanPlayer = new HumanPaddle(1);
        ball = new Ball();
        thread = new Thread(this);
        thread.start();
    }

    public void update(Graphics g) {
        paint(g);
    }


    public void paint(Graphics g) {
        g.setColor(Color.white);
        g.fillRect(0, 0, WIDTH, HEIGHT);
        if (ball.getX() < -10 || ball.getX() > 710) {
            g.setColor(Color.black);
            g.drawString("Game Over", 350, 250);
        } else {
            humanPlayer.draw(g);
            ball.draw(g);
        }
    }

    public void run() {
        while(true) {
            humanPlayer.move();
            ball.move();

            repaint();
            try {
                Thread.sleep(10);
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
}
