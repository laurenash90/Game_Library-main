import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PongPlayMenu extends JPanel implements ActionListener {
    static JFrame prePlay;
    static JButton start = new JButton("Start");
    static JButton exit = new JButton("Exit");

    /**
     * Pre-play menu for game
     */
    public PongPlayMenu(){
        prePlay = new JFrame("Pong-Game");
        prePlay.add(drawImage());
        prePlay.setSize(1265, 688);

        start.addActionListener(this);
        exit.addActionListener(this);

        prePlay.setLocationRelativeTo(null);
        prePlay.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        prePlay.setVisible(true);
    }

    /**
     * Creates the background for the GUI window
     * @return the component
     */
    private static Component drawImage(){
        ImageIcon pongImage = new ImageIcon("src\\icons\\pongGame.png");
        JLabel myLabel = new JLabel(pongImage);
        myLabel.setSize(600,600);

        start.setBounds(543,500,165,30);
        prePlay.add(start);
        prePlay.add(exit);
        exit.setBounds(543,550, 165,30);
        start.setBorder(null);
        start.setBorderPainted(false);
        start.setFont(new Font("Serif", Font.BOLD, 20 ));
        exit.setFont(new Font("Serif", Font.BOLD, 20 ));
        exit.setBorder(null);
        exit.setBorderPainted(false);


        return myLabel;

    }

    /**
     *
     * @param e the event to be processed
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == start){
            prePlay.dispose();
            new PongFrame();
            new PongPanel();

        }

        if(e.getSource() == exit){
            prePlay.dispose();
            new LaunchPage();
        }
    }
}
