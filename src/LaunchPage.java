import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;

import java.awt.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LaunchPage extends JFrame implements ActionListener{
    
    
    //Icons for buttons
    ImageIcon snakeI = new ImageIcon("src//icons//snake.PNG");
    ImageIcon hangmanI = new ImageIcon("src//icons//hangman.PNG");
    ImageIcon memoryI = new ImageIcon("src//icons//memorygame.PNG");
    ImageIcon tictactoeI = new ImageIcon("src//icons//tictactoe.PNG");
    ImageIcon blockBreakerI = new ImageIcon("src//icons//blockbreaker.jpg");
    ImageIcon pongI = new ImageIcon("src//icons//pong.png");
    //Buttons to access games
    JFrame frame = new JFrame();

    JButton snake = new JButton(snakeI);
    JButton hangman = new JButton(hangmanI);
    JButton memory = new JButton(memoryI);
    JButton tictactoe = new JButton(tictactoeI);
    JButton blockBreaker = new JButton(blockBreakerI);
    JButton pong = new JButton(pongI);



    LaunchPage(){
        snake.setBounds(10, 10, 300, 228);
        snake.setFocusable(false);
        snake.addActionListener(this);
        hangman.setBounds(310, 10, 298, 146);
        hangman.setFocusable(false);
        hangman.addActionListener(this);
        memory.setBounds(310, 156, 243, 97);
        memory.setFocusable(false);
        memory.addActionListener(this);

        tictactoe.setBounds(310, 400, 204, 175);
        tictactoe.setFocusable(false);
        tictactoe.addActionListener(this);

        blockBreaker.setBounds(310, 253, 310, 146);
        blockBreaker.setFocusable(false);
        blockBreaker.addActionListener(this);

        pong.setBounds(10, 238, 300, 280);
        pong.setFocusable(false);
        pong.addActionListener(this);

        frame.getContentPane().setBackground(Color.DARK_GRAY);
        frame.add(snake);
        frame.add(hangman);
        frame.add(memory);
        frame.add(blockBreaker);
        frame.add(pong);
        frame.add(tictactoe);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700,700);
        frame.setLayout(new GridLayout(3,2,50,50));
        frame.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==snake){
           new SnakePrePlayMenu();
           frame.dispose();
        }
        if(e.getSource() == blockBreaker){
            new BlockBreakerFrame();
            frame.dispose();
        }
        if(e.getSource() == pong){
            new PongPlayMenu();
            frame.dispose();
        }
        if(e.getSource() == tictactoe){
            new TicTacToeEngine();
            frame.dispose();
        }
        if(e.getSource() == memory){
            new MemoryGame();
            frame.dispose();
        }
        if(e.getSource() == hangman){
            new HangmanFrame();
            frame.dispose();
        }
    }
    
}
