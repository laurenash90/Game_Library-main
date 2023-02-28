
import java.util.ArrayList;
import java.util.Locale;
import java.util.Random;

/**
 * Logic and gameplay for the Hangman Game.
 *
 * @author Lucas Myers
 * @version Spring 2022
 */
public class Hangman {

  /**
   * Bank of words the computer will choose from.
   */
  public static final String[] WORD_BANK = {"ABSTRACT", "ASSERT", "BOOLEAN",
      "BREAK", "BYTE", "CASE", "CATCH",
      "CHAR", "CLASS", "CONST", "CONTINUE", "DEFAULT",
      "DO", "DOUBLE", "ELSE", "ENUM", "EXTENDS",
      "FINAL", "FINALLY", "FLOAT", "FOR", "GOTO", "IF", "IMPLEMENTS", "IMPORT", "INSTANCEOF",
      "INT", "INTERFACE", "LONG", "NATIVE", "NEW", "PACKAGE", "PRIVATE", "PROTECTED", "PUBLIC",
      "RETURN", "SHORT", "STATIC", "STRICTFP", "SUPER",
      "SWITCH", "SYNCHRONIZED", "THIS", "THROW",
      "THROWS", "TRANSIENT", "TRY", "VOID", "VOLATILE", "WHILE"};

  /**
   * Random number generator used to select a word.
   */
  public static final Random RANDOM = new Random();

  /**
   * Number of errors allowed.
   */
  private final int numErrors = 6;

  /**
   * Word the player will be guessing for.
   */
  private String chosenWord;

  /**
   * Characters in the word the player will be guessing for.
   */
  private static char[] chosenWordLetters;

  /**
   * Variable to keep track of the player's number of errors.
   */
  private int errorCounter;

  /**
   * Array of characters the user has guessed.
   */
  private ArrayList<String> guessedLetters = new ArrayList<String>();

  /**
   * Getter method that returns the letters the player has guessed.
   *
   * @return the letters the player guessed as a string
   */
  public String getGuessedLetters() {
    return String.join(", ", guessedLetters);
  }

  /**
   * Getter method that returns the word the player is trying to guess.
   *
   * @return the word the player is trying to guess
   */
  public String getChosenWord() {
    return chosenWord;
  }

  /**
   * Getter method that returns the current number of errors.
   *
   * @return the current number of errors
   */
  public int getErrorCounter() {
    return errorCounter;
  }

  /**
   * Setter method that sets the word the player is guessing for.
   *
   * @param chosenWord The word the player is guessing for
   */
  public void setChosenWord(String chosenWord) {
    this.chosenWord = chosenWord;
    chosenWordLetters = new char[chosenWord.length()];
    for (int i = 0; i < chosenWord.length(); i++) {
      chosenWordLetters[i] = '_';
    }
    guessedLetters.clear();
  }

  /**
   * Constructor that initializes the game upon first launching.
   */
  public Hangman() {
    errorCounter = 0;
    chosenWord = pickRandomWord();
    chosenWordLetters = new char[chosenWord.length()];
    for (int i = 0; i < chosenWord.length(); i++) {
      chosenWordLetters[i] = '_';
    }
  }

  /**
   * Method to select a random word from the WORD_BANK.
   *
   * @return The random word selected from WORD_BANK
   */
  private String pickRandomWord() {
    return WORD_BANK[RANDOM.nextInt(WORD_BANK.length)];
  }

  /**
   * Method to reset the counters from previous game,
   * selects a new word, clears arrays.
   */
  public void newGame() {
    //Reset counters and arrays
    errorCounter = 0;
    guessedLetters.clear();
    //Pick a new word
    chosenWord = pickRandomWord();
    //Set up the blanks for chosen word
    chosenWordLetters = new char[chosenWord.length()];
    for (int i = 0; i < chosenWord.length(); i++) {
      chosenWordLetters[i] = '_';
    }
  }

  /**
   * Method to determine if a guessed letter is in the word,
   * if not error is incremented. If the input is not one letter, throws
   * error.
   *
   * @param g Player's one character guess
   * @return 0 if guess is not part of the word. 1 if guess is part of the word.
   *     2 if guess has been guessed already.
   * @throws IllegalArgumentException when the input is not a single
   *                                  alphabetic character
   */
  public int guess(String g) {
    if (g.length() != 1) {
      throw new IllegalArgumentException();
    }
    if (!g.matches("[a-zA-Z]+")) {
      throw new IllegalArgumentException();
    } else {
      g = g.toUpperCase();
      if (!guessedLetters.contains(g)) {
        if (chosenWord.contains(g)) {
          int i = chosenWord.indexOf(g);

          while (i >= 0) {
            chosenWordLetters[i] = g.charAt(0);
            i = chosenWord.indexOf(g, i + 1);
          }
          guessedLetters.add(g);
          return 1;
        } else {
          guessedLetters.add(g);
          errorCounter++;
          return 0;
        }
      }
      return 2;
    }
  }

  /**
   * Method to reset the counters from previous game,
   * selects a new word, clears arrays.
   *
   * @return True if the word has been guessed, false otherwise
   */
  public boolean completenessCheck() {
    return chosenWord.contentEquals(new String(chosenWordLetters));
  }

  /**
   * Method to display the progress of the word the player is guessing.
   *
   * @return The progress of the word the player is guessing
   */
  public String displayProcess() {
    StringBuilder builder = new StringBuilder();

    for (int i = 0; i < chosenWordLetters.length; i++) {
      builder.append(chosenWordLetters[i]);

      if (i < chosenWordLetters.length - 1) {
        builder.append(" ");
      }
    }
    return builder.toString();
  }
}
