import java.awt.*;
import java.awt.event.KeyEvent;

public class PongPaddles extends Rectangle {

    int id;
    int yVelocity;
    int speed = 9;

    /**
     * Constructor to build paddles
     * @param x sets x position
     * @param y sets y position
     * @param PADDLE_WIDTH constant for width of paddle
     * @param PADDLE_HEIGHT constant for height of paddle
     * @param id player 1 or 2
     */
    PongPaddles(int x, int y, int PADDLE_WIDTH, int PADDLE_HEIGHT, int id){
        super(x, y, PADDLE_WIDTH, PADDLE_HEIGHT);
        this.id = id;

    }

    /**
     *
     * @param e get key pressed
     */
    public void keyPressed(KeyEvent e){
        switch (id) {
            case 1 : {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(speed);
                    move();
                }
            }
            case 2 : {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(-speed);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(speed);
                    move();
                }
            }
        }
    }

    /**
     *
     * @param e get key released
     */
    public void keyReleased(KeyEvent e){
        switch (id) {
            case 1 : {
                if (e.getKeyCode() == KeyEvent.VK_W) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_S) {
                    setYDirection(0);
                    move();
                }
            }
            case 2 : {
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYDirection(0);
                    move();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                    setYDirection(0);
                    move();
                }
            }
        }
    }

    /**
     *
     * @param yDirection sets the movement along y-axis for paddle
     */
    public void setYDirection(int yDirection){
        yVelocity = yDirection;
    }

    /**
     * Method to move the paddles
     */
    public void move(){
        y = y + yVelocity;
    }

    /**
     *
     * @param graphics Method to draw game graphics
     */
    public void draw(Graphics graphics){
        if(id == 1)
            graphics.setColor(Color.BLUE);
        else
            graphics.setColor(Color.RED);
        graphics.fillRect(x, y, width, height);
    }
}
