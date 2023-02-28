import junit.framework.TestCase;
import org.junit.Test;

/**
 * MemoryGameTest class: JUnit test for MemoryGame.
 *
 * @author Lauren Inman
 *
 * @version Summer 2021
 */
public class MemoryGameTest extends MemoryGame {

  /**
   * Tests isGameWon method when game is not over.
   */
  @Test
  public void testIsGameWon() {
    matches = 3;
    TestCase.assertFalse("Game is NOT Won, Matches = 3", isGameWon());
  }

  /**
   * Tests isGameWon method when game is over.
   */
  @Test
  public void testIsGameWon2() {
    matches = 8;
    TestCase.assertTrue("Game is Won, Matches = 8", isGameWon());
  }
}