import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

/**
 * JFrame for Hangman.
 *
 * @author Lucas Myers
 * @version Spring 2022
 */
public class HangmanFrame extends JFrame implements ActionListener {

  JFrame hangFrame = new JFrame();
  JMenuBar menuBar;
  JMenu exitMenu;
  JMenu playMenu;
  JMenuItem library;
  JMenuItem playAgain;

  /**
   * Constructor that adds menu items and launches the hangman panel.
   */
  HangmanFrame() {
    //creates menu items
    menuBar = new JMenuBar();
    exitMenu = new JMenu("Exit");
    playMenu = new JMenu("Play Again");
    library = new JMenuItem("Exit to Library");
    playAgain = new JMenuItem("Play Again");

    exitMenu.add(library);
    playMenu.add(playAgain);
    menuBar.add(exitMenu);
    menuBar.add(playMenu);
    hangFrame.setJMenuBar(menuBar);

    library.addActionListener(this);
    playAgain.addActionListener(this);

    hangFrame.setSize(600, 600);
    hangFrame.add(new HangmanPanel());
    hangFrame.setTitle("Hangman");
    hangFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    hangFrame.setResizable(false);
    //hangFrame.pack();
    hangFrame.setVisible(true);
    hangFrame.setLocationRelativeTo(null);
  }

  /**
   * Receives action events.
   *
   * @param e the event to be processed
   */
  @Override
  public void actionPerformed(ActionEvent e) {
    if (e.getSource() == library) {
      hangFrame.dispose();
      new LaunchPage();
    }

    if (e.getSource() == playAgain) {
      hangFrame.dispose();
      new HangmanPanel();
      new HangmanFrame();
    }
  }
}
