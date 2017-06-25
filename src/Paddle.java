import java.awt.*;

/**
 * Created by Logan on 6/9/2017.
 */
interface Paddle {

    void draw(Graphics g);
    void move();
    int getY();
    int getX();
    int getPdlWidth();
    int getPdlHeight();
}
