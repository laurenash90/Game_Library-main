import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import org.junit.Test;

/**
 * JUnit tests for hangman.
 *
 * @author Lucas Myers
 * @version Spring 2022
 */
public class TestHangman {

  /**
   * Tests the constructor, ensures correct initial values.
   */
  @Test
  public void testConstructor() {
    Hangman game = new Hangman();
    assertEquals(game.getErrorCounter(), 0);
    assertEquals(game.getGuessedLetters().length(), 0);
    assertTrue(Arrays.asList(Hangman.WORD_BANK).contains(game.getChosenWord()));
  }

  /**
   * Tests the newGame method. Ensures correct values after starting
   * a new game.
   */
  @Test
  public void testNewGame() {
    Hangman game = new Hangman();
    game.newGame();
    assertEquals(game.getErrorCounter(), 0);
    assertEquals(game.getGuessedLetters().length(), 0);
    assertTrue(Arrays.asList(Hangman.WORD_BANK).contains(game.getChosenWord()));
  }

  /**
   * Tests the guess method for a correct guess.
   */
  @Test
  public void testValidGuess() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    assertEquals(game.guess("t"), 1);
    assertTrue(game.getGuessedLetters().contains("T"));
    assertEquals(game.getErrorCounter(), 0);
    assertEquals(game.displayProcess(), "T _ _ T");
  }

  /**
   * Tests the guess method for an incorrect guess.
   */
  @Test
  public void testWrongGuess() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    assertEquals(game.guess("w"), 0);
    assertTrue(game.getGuessedLetters().contains("W"));
    assertEquals(game.getErrorCounter(), 1);
    assertEquals(game.displayProcess(), "_ _ _ _");
  }

  /**
   * Tests the guess method for a guess that has already been tried.
   */
  @Test
  public void testAlreadyGuess() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess("s");
    assertTrue(game.getGuessedLetters().contains("S"));
    assertEquals(game.guess("s"), 2);
    assertEquals(game.getErrorCounter(), 0);
    assertEquals(game.displayProcess(), "_ _ S _");
  }

  /**
   * Tests the guess method for an invalid guess - more than one
   * character.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGuess1() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess("test");
  }

  /**
   * Tests the guess method for an invalid guess - numerical character.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGuess2() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess("7");
  }

  /**
   * Tests the guess method for an invalid guess - punctuation.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidGuess3() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess(".");
  }

  /**
   * Tests the completenessCheck method when the word has been completed.
   */
  @Test
  public void testComplete() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess("t");
    game.guess("e");
    game.guess("s");
    assertTrue(game.completenessCheck());
  }

  /**
   * Tests the completenessCheck method when the word is incomplete.
   */
  @Test
  public void testIncomplete() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    game.guess("e");
    game.guess("s");
    assertFalse(game.completenessCheck());
  }

  /**
   * Tests the displayProcess method by ensuring the word is updated as
   * the player guesses correct letters.
   */
  @Test
  public void testDisplayProcess() {
    Hangman game = new Hangman();
    game.setChosenWord("TEST");
    assertEquals(game.displayProcess(), "_ _ _ _");
    game.guess("t");
    assertEquals(game.displayProcess(), "T _ _ T");
    game.guess("e");
    assertEquals(game.displayProcess(), "T E _ T");
    game.guess("s");
    assertEquals(game.displayProcess(), "T E S T");
  }
}
