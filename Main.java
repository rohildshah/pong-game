import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Main extends JPanel implements KeyListener {

    final static Color BACKGROUND_COLOR = new Color(0, 0, 0);

    final static int SCREEN_HEIGHT = 500;
    final static int SCREEN_LENGTH = 800;

    final static int PADDLE_LENGTH = 50;
    final static int PADDLE_WIDTH = 10;


    Paddle lPad = new Paddle();
    Paddle rPad = new Paddle();

    Pellet pellet = new Pellet();

    boolean lDown; //s key pressed
    boolean lUp; //w key pressed
    boolean rDown; //down arrow pressed
    boolean rUp; //up arrow pressed

    int rY = SCREEN_HEIGHT/2; //starting y position for right paddle
    int lY = SCREEN_HEIGHT/2; //starting y position for left paddle
    int lX = (int) (0.1*SCREEN_LENGTH); //starting x position for left paddle
    int rX = (int) (0.9*SCREEN_LENGTH) - PADDLE_WIDTH; //starting x position for right paddle


    public void paintComponent(Graphics g) {
        if (lUp && lY != 0) { //top boundary for left paddle
            lY -= 10;
        } else if (lDown && lY != SCREEN_HEIGHT-PADDLE_LENGTH) { //bottom boundary for left paddle
            lY += 10;
        }

        if (rUp && rY != 0) { //top boundary for right paddle
            rY -= 10;
        } else if (rDown && rY != SCREEN_HEIGHT-PADDLE_LENGTH) { //bottom boundary for right paddle
            rY += 10;
        }


        lPad.paint(g, lX, lY); //paint left paddle (see Paddle.java)
        rPad.paint(g, rX, rY); //paint right paddle (see Paddle.java)
        pellet.paint(g, lY, rY, lX, rX); //paint pellet, pass current positions of paddles

    }

    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == 40) { //down arrow pressed
            rDown = true;
        } else if (e.getKeyCode() == 38) { //up arrow pressed
            rUp = true;
        } else if (e.getKeyCode() == 83) { //s key pressed
            lDown = true;
        } else if (e.getKeyCode() == 87) { //w key pressed
            lUp = true;
        }

    }

    public void keyReleased(KeyEvent e) {
        if (e.getKeyCode() == 40) { //down arrow released
            rDown = false;
        } else if (e.getKeyCode() == 38) { //up arrow released
            rUp = false;
        } else if (e.getKeyCode() == 83) { //s key released
            lDown = false;
        } else if (e.getKeyCode() == 87) { //w key released
            lUp = false;
        }
    }

    public void keyTyped(KeyEvent e) {} //must include this b/c of interface requirements

    public Main() {
        Timer timer = new Timer(30, arg0 -> repaint()); //starts timer for painting to occur
        timer.start(); //painting now occurs every 0.03 seconds
    }


    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Main panel = new Main();
            panel.setBackground(BACKGROUND_COLOR);

            panel.addKeyListener(panel);
            panel.setFocusable(true);

            JFrame frame = new JFrame("Pong");
            frame.setSize(SCREEN_LENGTH, SCREEN_HEIGHT+22);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.getContentPane().add(panel, BorderLayout.CENTER);
            frame.setVisible(true);
        });
    }
}
