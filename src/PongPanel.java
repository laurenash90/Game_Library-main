import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PongPanel extends JPanel implements Runnable{

    static final int GAME_WIDTH = 1000;
    static final int GAME_HEIGHT = (int)(GAME_WIDTH * (0.5555));
    static final Dimension SCREE_SIZE = new Dimension(GAME_WIDTH, GAME_HEIGHT);
    static final int BALL_DIAMETER = 20;
    static final int PADDLE_WIDTH = 25;
    static final int PADDLE_HEIGHT = 100;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    PongPaddles paddle1;
    PongPaddles paddle2;
    PongBall ball;
    PongScore score;

    /**
     * Constructor to play game
     */
    PongPanel(){
        newPaddles();
        newBall();
        score = new PongScore(GAME_WIDTH, GAME_HEIGHT);
        this.setFocusable(true);
        this.addKeyListener(new AL());
        this.setPreferredSize(SCREE_SIZE);

        gameThread = new Thread(this);
        gameThread.start();


    }

    /**
     * spawns new ball
     */
    public void newBall(){
        random = new Random();
        ball = new PongBall((GAME_WIDTH / 2) - (BALL_DIAMETER / 2), random.nextInt(GAME_HEIGHT - BALL_DIAMETER),
                BALL_DIAMETER, BALL_DIAMETER);

    }

    /**
     * spawns new paddles
     */
    public void newPaddles(){
        paddle1 = new PongPaddles(0, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 1);
        paddle2 = new PongPaddles(GAME_WIDTH - PADDLE_WIDTH, (GAME_HEIGHT / 2) - (PADDLE_HEIGHT / 2),
                PADDLE_WIDTH, PADDLE_HEIGHT, 2);

    }

    /**
     * creates ghraphics for the game
     * @param g  the <code>Graphics</code> context in which to paint
     */
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image, 0,0, this);
    }

    /**
     *
     * @param g draws the games graphics
     */
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }

    /**
     * Moves the players paddles
     */
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }

    /**
     * Checks to see if ball hits paddle and then bounces off
     */
    public void checkCollision(){
        //bounce ball
        if(ball.y <= 0)
            ball.setYDirection(-ball.yVelocity);
        if(ball.y >= GAME_HEIGHT - BALL_DIAMETER)
            ball.setYDirection(-ball.yVelocity);

        //bounce off paddles
        if(ball.intersects(paddle1)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.setXDirection(ball.xVelocity);
            ball.setYDirection(ball.yVelocity);
        }
        if(ball.intersects(paddle2)){
            ball.xVelocity = Math.abs(ball.xVelocity);
            ball.setXDirection(-ball.xVelocity);
            ball.setYDirection(-ball.yVelocity);
        }

        //stops paddles at window edges
        if(paddle1.y <= 0)
            paddle1.y = 0;
        if(paddle1.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle1.y = GAME_HEIGHT - PADDLE_HEIGHT;
        if(paddle2.y <= 0)
            paddle2.y = 0;
        if(paddle2.y >= (GAME_HEIGHT - PADDLE_HEIGHT))
            paddle2.y = GAME_HEIGHT - PADDLE_HEIGHT;

        //give score and creates new paddles and ball
        if(ball.x <= 0){
            score.player2++;
            newPaddles();
            newBall();
        }
        if(ball.x >= GAME_WIDTH - BALL_DIAMETER){
            score.player1++;
            newPaddles();
            newBall();
        }
    }

    /**
     * Runs the game with a timer function
     */
    public void run(){
        long lastTime = System.nanoTime();
        double amountOfTicks = 60;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while (true) {
            long now = System.nanoTime();
            delta += (now - lastTime) / ns;
            lastTime = now;
            if (delta >= 1) {
                move();
                checkCollision();
                repaint();
                delta--;
            }
        }
    }

    /**
     * Integrated class for key actions
     */
    public class AL extends KeyAdapter{
        /**
         *
         * @param e the event to be processed
         */
        public void keyPressed(KeyEvent e){
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

            if(e.getKeyCode() == KeyEvent.VK_ESCAPE){
                new PongPlayMenu();
            }
        }

        /**
         *
         * @param e the event to be processed
         */
        public void keyReleased(KeyEvent e){
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
