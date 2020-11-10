

//STUDENTS SHOULD ADD CLASS COMMENTS, METHOD COMMENTS, FIELD COMMENTS 

import java.io.PrintWriter;

/**
 * Provides an implementation for a board object in a tic-tac-toe game
 * @author odladitan
 *
 */
public class Board implements Constants {
	private char theBoard[][];
	private int markCount;



	private PrintWriter socketOut;

	public Board() {
		markCount = 0;
		theBoard = new char[3][];
		for (int i = 0; i < 3; i++) {
			theBoard[i] = new char[3];
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		}
	}

	/**
	 *  Returns the mark 'X' or 'O' which is at a particular square on the board 
	 * @param row board row
	 * @param col board column
	 * @return 'X' or 'O'
	 */
	public char getMark(int row, int col) {
		return theBoard[row][col];
	}

	/**
	 * Returns true if there is no longer any empty square on the board else returns false
	 * @return true or false
	 */
	public boolean isFull() {
		return markCount == 9;
	}

	/**
	 * Returns true if the 'X' player has won the game else returns false
	 * @return true or false
	 */
	public boolean xWins() {
		if (checkWinner(LETTER_X) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Returns true if the 'O' has won the game else returns false
	 * @return true or false
	 */
	public boolean oWins() {
		if (checkWinner(LETTER_O) == 1)
			return true;
		else
			return false;
	}

	/**
	 * Displays the tic-tac-toe board
	 */
	public void display() {
		displayColumnHeaders();
		addHyphens();
		for (int row = 0; row < 3; row++) {
			addSpaces();
			socketOut.print("    row " + row + ' ');
			for (int col = 0; col < 3; col++)
				socketOut.print("|  " + getMark(row, col) + "  ");
			socketOut.println("|");
			addSpaces();
			addHyphens();
		}
	}

	/**
	 * Adds a mark to a square on the board indicating where a player has made his move  
	 * @param row board row 
	 * @param col board column
	 * @param mark 'X' or 'O'
	 */
	public void addMark(int row, int col, char mark) {
		
		theBoard[row][col] = mark;
		markCount++;
	}

	/**
	 * Clears all the marks on the board to generate an empty board
	 */
	public void clear() {
		for (int i = 0; i < 3; i++)
			for (int j = 0; j < 3; j++)
				theBoard[i][j] = SPACE_CHAR;
		markCount = 0;
	}

	/**
	 * Checks if player with mark 'X' or 'O' is the winner of the game
	 * @param mark 'X' or 'O'
	 * @return returns 1 0r 0 to indicate whether the player with the given mark won the game or not.
	 */
	int checkWinner(char mark) {
		int row, col;
		int result = 0;

		for (row = 0; result == 0 && row < 3; row++) {
			int row_result = 1;
			for (col = 0; row_result == 1 && col < 3; col++)
				if (theBoard[row][col] != mark)
					row_result = 0;
			if (row_result != 0)
				result = 1;
		}

		
		for (col = 0; result == 0 && col < 3; col++) {
			int col_result = 1;
			for (row = 0; col_result != 0 && row < 3; row++)
				if (theBoard[row][col] != mark)
					col_result = 0;
			if (col_result != 0)
				result = 1;
		}

		if (result == 0) {
			int diag1Result = 1;
			for (row = 0; diag1Result != 0 && row < 3; row++)
				if (theBoard[row][row] != mark)
					diag1Result = 0;
			if (diag1Result != 0)
				result = 1;
		}
		if (result == 0) {
			int diag2Result = 1;
			for (row = 0; diag2Result != 0 && row < 3; row++)
				if (theBoard[row][3 - 1 - row] != mark)
					diag2Result = 0;
			if (diag2Result != 0)
				result = 1;
		}
		return result;
	}
	

	/**
	 * Displays the column headers on the board 
	 */
	void displayColumnHeaders() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|col " + j);
		socketOut.println();
	}

	/**
	 * Adds the horizontal lines on the board 
	 */
	void addHyphens() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("+-----");
		socketOut.println("+");
	}

	/**
	 * Adds the spaces on the board
	 */
	void addSpaces() {
		socketOut.print("          ");
		for (int j = 0; j < 3; j++)
			socketOut.print("|     ");
		socketOut.println("|");
	}
	
	char[][] getTestBoard(){
		return cloneArray(theBoard);
	}
	
	public char[][] cloneArray(char[][] src) {
		int length = src.length;
		char[][] target = new char[length][src[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(src[i], 0, target[i], 0, src[i].length);
		}
		return target;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}
}
