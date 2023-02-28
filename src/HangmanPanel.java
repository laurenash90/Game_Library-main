import java.awt.Color;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


/**
 * JPanel for Hangman.
 *
 * @author Lucas Myers
 * @version Spring 2022
 */
public class HangmanPanel extends JPanel {

  //declare jbuttons and text fields
  private Hangman game;
  private JLabel lblProgress;
  private JLabel lblErrors;
  private JLabel lblGuessed;
  private JButton guessButton;
  private JTextField guessField;

  /**
   * Constructor that sets buttons, labels, and text fields. Adds an
   * action listener.
   */
  public HangmanPanel() {

    game = new Hangman();

    setLayout(new GridLayout(10, 2));
    setBackground(Color.lightGray);

    add(new JLabel("Progress: "));
    lblProgress = new JLabel();
    lblProgress.setText(game.displayProcess());
    lblProgress.setHorizontalAlignment(SwingConstants.CENTER);
    lblProgress.setFont(new Font("SansSerifBold", Font.PLAIN, 50));
    add(lblProgress);

    guessField = new JTextField("Enter a letter");
    add(guessField);

    guessButton = new JButton("Guess");
    add(guessButton);

    add(new JLabel("Errors: "));
    lblErrors = new JLabel();
    String errors = String.valueOf(game.getErrorCounter());
    lblErrors.setText(errors);
    lblErrors.setFont(new Font("SansSerif", Font.PLAIN, 20));
    add(lblErrors);

    add(new JLabel("Guessed Letters: "));
    lblGuessed = new JLabel();
    lblGuessed.setText(game.getGuessedLetters());
    lblGuessed.setFont(new Font("SansSerif", Font.PLAIN, 20));
    add(lblGuessed);


    guessButton.addActionListener(new ButtonListener());

    JOptionPane.showMessageDialog(null, "Welcome to Hangman! "
            + "You will be guessing Java Keywords. "
            + "Guess one alphabetic character at a time, 6 errors and it's "
            + "game over.");

  }

  /**
   * Method that initializes the labels and text fields and resets
   * the hangman game.
   */
  private void newGame() {
    game.newGame();
    lblProgress.setText(game.displayProcess());
    String errors = String.valueOf(game.getErrorCounter());
    lblErrors.setText(errors);
    lblGuessed.setText(game.getGuessedLetters());
    guessField.setText("Enter a letter");
  }


  private class ButtonListener implements ActionListener {

    /**
     * Receives action events.
     *
     * @param event the event to be processed
     */
    public void actionPerformed(ActionEvent event) {

      if (event.getSource() == guessButton) {
        try {
          switch (game.guess(guessField.getText())) {
            case (0):
              JOptionPane.showMessageDialog(null, "Incorrect!");
              break;
            case (1):
              JOptionPane.showMessageDialog(null, "Correct");
              break;
            case (2):
              JOptionPane.showMessageDialog(null, "You already guessed this letter!");
              break;
          }
        } catch (IllegalArgumentException e) {
          JOptionPane.showMessageDialog(null, "Please enter ONE ALPHABETIC character!");
        }
      }

      lblProgress.setText(game.displayProcess());
      String errors = String.valueOf(game.getErrorCounter());
      lblErrors.setText(errors);
      lblGuessed.setText(game.getGuessedLetters());

      if (game.completenessCheck()) {
        String[] responses = {"Play Again", "Game Library"};
        ImageIcon icon = new ImageIcon("src//Images//hmwin.PNG");
        int choice = JOptionPane.showOptionDialog(
                null,
                "You Win!",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                responses,
                0);
        //Play Again
        if (choice == JOptionPane.YES_OPTION) {
          newGame();
        }
        //Back to Game Library
        if (choice == JOptionPane.NO_OPTION) {
          ((Frame) HangmanPanel.this.getTopLevelAncestor()).dispose();
          new LaunchPage();
        }
      }

      if (game.getErrorCounter() == 6) {
        String[] responses = {"Play Again", "Game Library"};
        ImageIcon icon = new ImageIcon("src//Images//hmlose.JPEG");
        int choice = JOptionPane.showOptionDialog(
                null,
                "You Lose!",
                "Game Over",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.INFORMATION_MESSAGE,
                icon,
                responses,
                0);
        //Play Again
        if (choice == JOptionPane.YES_OPTION) {
          newGame();
        }
        //Back to Game Library
        if (choice == JOptionPane.NO_OPTION) {
          ((Frame) HangmanPanel.this.getTopLevelAncestor()).dispose();
          new LaunchPage();
        }
      }

    }
  }
}
