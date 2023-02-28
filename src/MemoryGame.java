import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Collections;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JToggleButton;
import javax.swing.Timer;

/**
 * MemoryGame class: Runs the memory game.
 *
 * @author Lauren Inman
 *
 * @version Summer 2021
 */
public class MemoryGame extends JPanel implements ActionListener {
  public JToggleButton selectedCard;
  public JToggleButton b1;
  public JToggleButton b2;
  private final Timer timer;
  private final JFrame frame;
  private final JLabel keepscore;
  private final JButton gamelibrary;
  public int matches;
  private int score;

  /**
   * Method that sets up the board for game play.
   */
  public MemoryGame() {
    //Frame
    frame = new JFrame();
    frame.setTitle("Memory Game");
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setSize(600, 600);
    frame.setResizable(false);
    frame.getContentPane().setBackground(Color.GRAY);
    frame.setLayout(new FlowLayout());

    //Score Label
    keepscore = new JLabel("Score: " + score, JLabel.LEFT);
    keepscore.setFont(new Font("Bro", Font.PLAIN, 20));

    //Game Library Button
    gamelibrary = new JButton("Game Library");
    gamelibrary.addActionListener(this);

    //Card Panel
    JPanel cardPanel = new JPanel();
    cardPanel.setBackground(Color.GRAY);
    cardPanel.setPreferredSize(new Dimension(525, 525));
    cardPanel.setLayout(new GridLayout(4, 4));
    cardPanel.setVisible(true);

    //ImageIcon for cardback
    ImageIcon cardback = new ImageIcon("src//Images//jello2.png");
    String[] pics = {
        "src//Images//angela.png",
        "src//Images//dwight.png",
        "src//Images//jim.png",
        "src//Images//kevin.png",
        "src//Images//michael.png",
        "src//Images//pam.png",
        "src//Images//stanley.png",
        "src//Images//toby.png"
    };
    ArrayList<ImageIcon> cardface = new ArrayList<>();

    //Creates cardface ImageIcon array from string array pics
    for (int i = 0; i < pics.length; i++) {
      cardface.add(new ImageIcon(pics[i]));
      cardface.add(new ImageIcon(pics[i]));
    }

    //Shuffles array cardface
    Collections.shuffle(cardface);

    //set up the timer
    timer = new Timer(500, new ActionListener() {
      public void actionPerformed(ActionEvent ae) {
        checkCards();
        score++;
        keepscore.setText("Score: " + score);

      }
    });
    timer.setRepeats(false);

    //Creates JButtons and adds to cardPanel
    for (int i = 0; i < 16; i++) {
      JToggleButton button = new JToggleButton(cardback, false);
      button.setSelectedIcon(cardface.get(i));
      button.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent ae) {
          selectedCard = button;
          doTurn();
        }
      });
      cardPanel.add(button);
    }
    frame.add(keepscore);
    frame.add(gamelibrary);
    frame.add(cardPanel);
    frame.setVisible(true);
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == gamelibrary) {
      new LaunchPage();
      frame.dispose();
    }
  }

  /**
   * Method for taking a turn.
   *
   */
  public void doTurn() {
    //No card has been selected yet
    if (b1 == null && b2 == null) {
      b1 = selectedCard;
      b1.setSelected(true);
    }
    //First card has been selected already
    if (b1 != null && b1 != selectedCard && b2 == null) {
      b2 = selectedCard;
      b2.setSelected(true);
      timer.start();
    }
  }

  /**
   * Method for checking selected cards for match or mismatch.
   */
  public void checkCards() {
    //Match
    if (b1.getSelectedIcon().toString().equals(b2.getSelectedIcon().toString())) {
      b1.setEnabled(false);
      b2.setEnabled(false);
      matches++;
      if (this.isGameWon()) {
        String[] responses = {"Play Again", "Game Library"};
        ImageIcon icon = new ImageIcon("src//Images//img.png");
        int choice = JOptionPane.showOptionDialog(
            null,
            "You Win!",
            "Game Over",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.INFORMATION_MESSAGE,
            icon,
            responses,
            0);
        //'Play Again' button
        if (choice == JOptionPane.YES_OPTION) {
          setPlayagain();
        }
        //'Game Library' button
        if (choice == JOptionPane.NO_OPTION) {
          new LaunchPage();
          frame.dispose();
        }
      }
    } else { //Mismatch
      b1.setSelected(false);
      b2.setSelected(false);
    }
    b1 = null;
    b2 = null;
  }

  /**
   * Method that checks if the game has been won.
   *
   * @return boolean
   */
  public boolean isGameWon() {
    return matches == 8;
  }

  /**
   * Method that sets the board up to play again.
   */
  public void setPlayagain() {
    main(null);
  }



  public static void main(String[] args) {
    new MemoryGame();

  }

}
