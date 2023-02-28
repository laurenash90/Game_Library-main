import java.awt.*;
import java.util.Random;

public class PongBall extends Rectangle {
    Random random;
    int xVelocity;
    int yVelocity;
    int initialSpeed = 8;

    /**
     * Constructor for pong ball
     * @param x sets x position for ball
     * @param y sets y position for ball
     * @param width sets width for ball
     * @param height sets height for ball
     */
    PongBall(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randomXDirection = random.nextInt(2);
        if(randomXDirection == 0)
            randomXDirection--;
        setXDirection(randomXDirection * initialSpeed);

        int randomYDirection = random.nextInt(2);
        if(randomYDirection == 0)
            randomYDirection--;
        setYDirection(randomYDirection * initialSpeed);
    }

    /**
     *
     * @param randomXDirection Direction for ball movement along the x-axis
     */
    public void setXDirection(int randomXDirection){
        xVelocity = randomXDirection;
    }

    /**
     *
     * @param randomYDirection Direction for ball movement along the y-axis
     */
    public void setYDirection(int randomYDirection){
        yVelocity = randomYDirection;
    }

    /**
     * Method to move the ball
     */
    public void move(){
        x += xVelocity;
        y += yVelocity;

    }

    /**
     *
     * @param g Method to draw game graphics
     */
    public void draw(Graphics g){
        g.setColor(Color.WHITE);
        g.fillOval(x, y, width, height);
    }
}
