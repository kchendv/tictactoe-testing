package com.example;

import org.jetbrains.annotations.NotNull;

/**
 * Takes in and evaluates a string representing a tic tac toe board.
 */
public class TicTacToeBoard {
  private final int boardWidth; // records the width of the board
  private final String board;
  private int xCount = 0; // stores the number of X's found on the board
  private int oCount = 0; // stores the number of O's found on the board

  /**
   * This method should load a string into your TicTacToeBoard class.
   * @param board The string representing the board
   */
  public TicTacToeBoard(@NotNull String board) throws IllegalArgumentException {
    if (board.equals("")) {
      throw new IllegalArgumentException();
    }

    /*
     * Checks if boardWidth is a perfect square
     * If not, throws IllegalArgumentException due to the board not having n x n size
     */
    boardWidth = (int) Math.sqrt(board.length());
    if (boardWidth != Math.sqrt(board.length())) {
      throw new IllegalArgumentException();
    }
    this.board = board.toUpperCase();

  }

  /**
   * Calculates xCount and oCount
   */
  private void countBoard(){
    for (int i = 0; i < board.length(); i++) {
      if (board.charAt(i) == 'X'){
        xCount++;
      } else if (board.charAt(i) == 'O'){
        oCount++;
      }
    }

  }

  /**
   * Identifies the latest player, or identifies an impossible board state
   * @return The integer value for xPlayedLast
   * 1 when X is the latest player, 0 if O is the latest player
   * -1 if the board state is impossible
   */
  private int countLastPlayer(){
    countBoard();

    if (xCount == oCount){
      return 0;
    } else if (xCount == oCount + 1){
      return 1;
    } else{
      return -1;
    }
  }

  /**
   * Checks if either player wins by occupying a full row, column or diagonal
   * modifies xWinCondition and oWinCondition
   * @param checkValue The character that is checked, either 'X' or 'O'
   */
  private boolean checkLine(char checkValue){
    /*
     * Checks if either player wins by occupying a full row
     */
    for (int row = 0; row < boardWidth; row++){
      boolean valuesIdentical = true;
      for (int col = 0; col < boardWidth; col++){
        if (board.charAt((row * boardWidth) + col) != checkValue) {
          valuesIdentical = false;
          break;
        }
      }
      if (valuesIdentical){
        return true;
      }
    }

    /*
     * Checks if either player wins by occupying a full column
     */
    for (int col = 0; col < boardWidth; col++) {
      boolean valuesIdentical = true;
      for (int row = 0; row < boardWidth; row++) {
        if (board.charAt((row * boardWidth) + col) != checkValue) {
          valuesIdentical = false;
          break;
        }
      }
      if (valuesIdentical) {
        return true;
      }
    }

    /*
     * Checks if either player wins by occupying a full diagonal
     */
    boolean valuesIdentical = true;
    for (int i = 0; i < boardWidth; i++) {
      if (board.charAt((i * boardWidth) + i) != checkValue) {
        valuesIdentical = false;
        break;
      }
    }
    if (valuesIdentical){
      return true;
    }

    valuesIdentical = true;
    for (int i = 0; i < boardWidth; i++) {
      if (board.charAt((i * boardWidth) + (boardWidth - i - 1)) != checkValue) {
        valuesIdentical = false;
        break;
      }
    }
    if (valuesIdentical){
      return true;
    }

    return false;
  }

  /**
   * Checks the state of the board (unreachable, no winner, X wins, or O wins)
   * @return an enum value corresponding to the board evaluation
   */
  public Evaluation evaluate() {
    /*
     * xPlayedLast has value 1 if X is the latest player, 0 if O is the latest player
     * -1 if the board state is impossible
     */
    int xPlayedLast = countLastPlayer();
    if (xPlayedLast == -1){
      return Evaluation.UnreachableState;
    }

    boolean xWinCondition = checkLine('X'); // true when X wins in any line of the board
    boolean oWinCondition = checkLine('O'); // true when O wins in any line of the board

    if (xPlayedLast == 1 && oWinCondition) {
      return Evaluation.UnreachableState; // Unreachable: X plays after O already won
    } else if (xPlayedLast == 0 && xWinCondition){
      return Evaluation.UnreachableState; // Unreachable: O plays after O already won
    } else if (xWinCondition){
      return Evaluation.Xwins;
    } else if (oWinCondition){
      return Evaluation.Owins;
    }

    return Evaluation.NoWinner;
  }
}