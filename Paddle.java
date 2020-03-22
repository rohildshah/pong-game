import java.awt.*;

public class Paddle {
    public final static Color PADDLE_COLOR = new Color(255, 255, 255);

    public void paint(Graphics g, int xpos, int ypos) {
        g.setColor(PADDLE_COLOR);
        g.fillRect(xpos, ypos, Main.PADDLE_WIDTH, Main.PADDLE_LENGTH);
    }
}
