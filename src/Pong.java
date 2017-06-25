import javax.swing.*;
/**
 * Created by Logan on 6/9/2017
 *
 * Main driver for the Pong game
 */
class Pong extends JFrame {
    private PongPanel panel;

    private Pong() {
        PongPanel pong = new PongPanel();
        getContentPane().add(pong);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(PongPanel.getFrameWidth(), PongPanel.getFrameHeight());
        setResizable(false);
        setTitle("Pong");
    }

    public static void main(String[] args) {
        new Pong();
    }
}
