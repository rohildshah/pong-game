import java.awt.*;

public class Pellet {
    public final static Color PELLET_COLOR = new Color(255, 255, 255);
    public final static int PELLET_SIZE = 10;
    public final static int BASE_SPEED = 5;

    int xpos = Main.SCREEN_LENGTH/2; //starting x position for pellet
    int ypos = Main.SCREEN_HEIGHT/2; //starting y position for pellet

    int bounce = 0; //record the number of times the paddle has hit the pellet in that game

    double direction = (Math.random()*2.0*Math.PI); //init direction of pellet with random radian 0<d<2pi
    int speed; //init speed of pellet; speed increases quadratically as bounce increases

    int rscore = 0; //init score for right paddle
    int lscore = 0; //init score for left paddle
    String score; //init string score to display on screen


    public void paint(Graphics g, int leftY, int rightY, int leftX, int rightX) {
        speed = (int) (0.03*Math.pow(bounce, 2)) + BASE_SPEED; //speed increases quadratically as bounce increases

        if (ypos <= 0) { //if pellet hits top
            if (Math.cos(direction) > 0.0) { //if pellet is moving right
                direction = (Math.random() * (Math.PI / 2.0)) + (3.0 * Math.PI / 2.0); //choose direction in QIV
            } else {
                direction = (Math.random() * (Math.PI / 2.0)) + (Math.PI); //choose direction in QIII
            }
        } else if ((ypos + PELLET_SIZE) >= Main.SCREEN_HEIGHT) { //if pellet hits bottom
            if (Math.cos(direction) > 0.0) { //if pellet is moving right
                direction = Math.random() * (Math.PI / 2.0); //choose direction in QI
            } else {
                direction = (Math.random() * (Math.PI / 2.0)) + (Math.PI / 2.0); //choose direction in QII
            }
        }

        if (xpos <= (leftX + Main.PADDLE_WIDTH)
                && xpos > leftX
                && (ypos + PELLET_SIZE) > leftY
                && ypos < (leftY + Main.PADDLE_LENGTH)) { //if pellet hits left paddle
            if (Math.sin(direction) > 0.0) { //if pellet is moving up
                direction = Math.random() * (Math.PI / 2.0); //choose direction in QI
            } else {
                direction = (Math.random() * (Math.PI / 2.0)) + (3.0 * Math.PI / 2.0); //choose direction in QIV
            }
            bounce++; //increment bounce
        }

        if ((xpos + PELLET_SIZE) >= rightX
                && xpos < (rightX + PELLET_SIZE)
                && (ypos + PELLET_SIZE) > rightY
                && ypos < (rightY + Main.PADDLE_LENGTH)) { //if pellet hits right paddle
            if (Math.sin(direction) > 0.0) { //if pellet is moving up
                direction = (Math.random() * (Math.PI / 2.0)) + (Math.PI / 2.0); //choose direction in QII
            } else {
                direction = (Math.random() * (Math.PI / 2.0)) + (Math.PI); //choose direction in QIII
            }
            bounce++; //increment bounce
        }

        if (xpos <= 0) { //if pellet hits left side of screen
            xpos = Main.SCREEN_LENGTH/2; //reset x position of pellet
            ypos = Main.SCREEN_HEIGHT/2; //reset y position of pellet
            direction = (Math.random()*2.0*Math.PI); //choose new random direction
            bounce = 0; //reset bounce count to reset speed
            rscore++; //increment right paddle score
        } else if ((xpos + PELLET_SIZE) >= Main.SCREEN_LENGTH) { //if pellet hits right side of screen
            xpos = Main.SCREEN_LENGTH/2; //reset x position of pellet
            ypos = Main.SCREEN_HEIGHT/2; //reset y position of pellet
            direction = (Math.random()*2.0*Math.PI); //choose new random direction
            bounce = 0; //reset bounce count to reset speed
            lscore++; //increment left paddle score
        }

        xpos += speed * Math.cos(direction); //move x position of pellet according to direction
        ypos -= speed * Math.sin(direction); //move y position of pellet according to direction

        g.setColor(PELLET_COLOR); //set white color
        g.fillRect(xpos, ypos, PELLET_SIZE, PELLET_SIZE); //draw pellet

        FontMetrics metrics = g.getFontMetrics(); //init string metrics

        score = "" + lscore + " | " + rscore; //create score to go at the top of the screen
        g.drawString(score, (Main.SCREEN_LENGTH-metrics.stringWidth(score))/2, 20); //write the score
    }
}
