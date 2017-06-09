import javax.swing.*;
/**
 * Created by Logan on 6/9/2017
 */
public class Pong extends JFrame {
    private PongPanel panel;

    public Pong() {
        PongPanel pong = new PongPanel(this);
        getContentPane().add(pong);
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(PongPanel.getFrameWidth(), PongPanel.getFrameHeight());
        setResizable(false);
        setTitle("Pong");
    }

    public static void main(String[] args) {
        new Pong();
    }
}
