import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SnakePrePlayMenu extends JFrame implements ActionListener {
    private static JFrame prePlay;
    static JButton exit = new JButton("Exit");
    static JButton start = new JButton("Start");


    SnakePrePlayMenu(){

        prePlay = new JFrame("Snake Game");
        prePlay.add(drawIcon());
        prePlay.setSize(600,600);

        start.addActionListener(this);
        exit.addActionListener(this);
        prePlay.setBackground(Color.WHITE);
        prePlay.setLocationRelativeTo(null);
        prePlay.setLayout(null);
        prePlay.setDefaultCloseOperation(EXIT_ON_CLOSE);
        prePlay.setVisible(true);
    }

    private static Component drawIcon(){
        ImageIcon snakeIcon = new ImageIcon("src\\icons\\snakeGame.PNG");
        JLabel myLabel = new JLabel(snakeIcon);

        myLabel.setSize(600, 600);

        //buttons
        start.setBounds(345,270,165,30);
        prePlay.add(start);
        prePlay.add(exit);
        exit.setBounds(345,310, 165,30);
        start.setBorder(null);
        start.setBorderPainted(false);
        start.setFont(new Font("Serif", Font.BOLD, 20 ));
        exit.setFont(new Font("Serif", Font.BOLD, 20 ));
        exit.setBorder(null);
        exit.setBorderPainted(false);


        return myLabel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            new SnakeGameFrame();
            new SnakeGamePanel();
            prePlay.dispose();
        }

        if(e.getSource() == exit){
            prePlay.dispose();
            new LaunchPage();
        }
    }
}
