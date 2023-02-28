import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

//@SuppressWarnings("ALL")
public class BlockBreakerGamePlay extends JPanel implements KeyListener, ActionListener {

    private boolean isRunning = false;
    private int score = 0;
    private int toatlBricks = 21;
    private final Timer time;
    private int playerX = 310;

    private int ballposX = 120;
    private int ballposY = 350;
    private int ballXdir = -5;
    private int ballYdir = -4;

    private BlockBreakerMapGen map;

    /**
     * Constructor to play BlockBreaker
     */

    public BlockBreakerGamePlay(){
        map = new BlockBreakerMapGen(3, 7);
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        int delay = 8;
        time = new Timer(delay, this);
        time.start();
    }

    /**
     * Paints the games graphics
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(1,1,692,592);

        map.draw((Graphics2D)g);

        g.setColor(Color.YELLOW);
        g.fillRect(0,0,3,592);
        g.fillRect(0,0,692,3);
        g.fillRect(683,0,3,592);

        g.setColor(Color.WHITE);
        g.setFont(new Font("serif", Font.BOLD, 25));
        g.drawString(""+score, 590, 30);

        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);

        g.setColor(Color.YELLOW);
        g.fillOval(ballposX, ballposY, 20, 20);

        if(toatlBricks <= 0){
            isRunning = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("You Won: ", 260, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        if(ballposY > 570){
            isRunning = false;
            ballXdir = 0;
            ballYdir = 0;
            g.setColor(Color.RED);
            g.setFont(new Font("serif", Font.BOLD, 30));
            g.drawString("Game Over, Scores: " + score, 190, 300);

            g.setFont(new Font("serif", Font.BOLD, 20));
            g.drawString("Press Enter to Restart", 230, 350);
        }

        g.dispose();
    }


    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        time.start();
        if(isRunning){
            if(new Rectangle(ballposX,ballposY,20,20).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYdir = -ballYdir;
            }

            A:for(int i = 0; i <map.map.length; i++){
                for(int j = 0; j < map.map[0].length; j++){
                    if(map.map[i][j] > 0){
                        int brickX = j * map.brickwidth + 80;
                        int brickY = i * map.brickheight + 50;
                        int brickWidth = map.brickwidth;
                        int brickHeight = map.brickheight;

                        Rectangle rect = new Rectangle(brickX,brickY, brickWidth, brickHeight);
                        Rectangle ballRect = new Rectangle(ballposX, ballposY, 20, 20);

                        if(ballRect.intersects(rect)){
                            map.setBrickValue(0, i, j);
                            toatlBricks--;
                            score += 5;

                            if(ballposX + 19 <= rect.x || ballposX + 1 >= rect.x + rect.width){
                                ballXdir = -ballXdir;
                            }else{
                                ballYdir = -ballYdir;
                            }
                            break A;
                        }
                    }
                }
            }

            ballposX += ballXdir;
            ballposY += ballYdir;
            if(ballposX < 0){
                ballXdir = -ballXdir;
            }
            if(ballposY < 0){
                ballYdir = -ballYdir;
            }
            if(ballposX > 670){
                ballXdir = -ballXdir;
            }
        }
        repaint();
    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 600){
                playerX = 600;
            }else{
                moveRight();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX < 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
        if(e.getKeyCode() == KeyEvent.VK_ENTER){
            if(!isRunning){
                isRunning = true;
                ballposX = 120;
                ballposY= 350;
                ballXdir = -5;
                ballYdir = -4;
                playerX = 310;
                score = 0;
                toatlBricks = 21;
                map = new BlockBreakerMapGen(3, 7);

                repaint();
            }
        }
    }

    /**
     * Method to move paddle right
     */
    public void moveRight(){
        isRunning = true;
        playerX+= 20;
    }

    /**
     * Method to move paddle left
     */
    public void moveLeft(){
        isRunning = true;
        playerX -= 20;
    }
}
