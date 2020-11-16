package com.example;

import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class TicTacToeBoardTest {
  /*
   * Test for proper conversion of characters
   */
  @Test
  public void testMixedCharacters() {
    TicTacToeBoard board = new TicTacToeBoard("DozXxXo4-");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  /*
   * Test for no winner in current board state
   */
  @Test
  public void testValidBoardNoWinner() {
    TicTacToeBoard board = new TicTacToeBoard("O...X.X..");
    assertEquals(Evaluation.NoWinner, board.evaluate());
  }

  /*
   * Test for valid board won by one player
   */
  @Test
  public void testWinRow() {
    TicTacToeBoard board = new TicTacToeBoard(".x.x.xooo");
    assertEquals(Evaluation.Owins, board.evaluate());
  }

  @Test
  public void testWinColumn() {
    TicTacToeBoard board = new TicTacToeBoard("o.X.OX..x");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  @Test
  public void testWinDiagonal() {
    TicTacToeBoard board = new TicTacToeBoard("xo.ox...x");
    assertEquals(Evaluation.Xwins, board.evaluate());
  }

  /*
   * Test for invalid board states
   */
  @Test
  public void testCountMismatch() {
    TicTacToeBoard board = new TicTacToeBoard("xxxoxooxx");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testAlreadyWonX() {
    TicTacToeBoard board = new TicTacToeBoard("xxxooo...");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  @Test
  public void testAlreadyWonO() {
    TicTacToeBoard board = new TicTacToeBoard("x.xooo.xx");
    assertEquals(Evaluation.UnreachableState, board.evaluate());
  }

  /*
   * Test for IllegalArgumentException
   * Code below derived from:
   * https://stackoverflow.com/questions/156503/how-do-you-assert-that-a-certain-exception-is-thrown-in-junit-4-tests
   */
  @Test(expected = IllegalArgumentException.class)
  public void testNonSquareBoard() {
    TicTacToeBoard board = new TicTacToeBoard("x.x...o");
  }

  @Test(expected = IllegalArgumentException.class)
  public void testEmptyString() {
    TicTacToeBoard board = new TicTacToeBoard("");
  }

}