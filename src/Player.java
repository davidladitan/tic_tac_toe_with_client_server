
import java.io.*;
import java.nio.Buffer;

/**
 * Provides a player object for the tic-tac-toe game
 *
 * @author David O. Laditan
 *
 */
public abstract class Player {

	protected String name;
	protected Board board;
	protected Player opponent;
	protected char mark;
	protected boolean isCurrentPlayer;
	protected BufferedReader socketIn;
	protected PrintWriter socketOut;

	/**
	 * Constructor of the player object
	 * @param name name of player
	 * @param mark mark of player, 'X' or 'O'
	 */
	public Player(String name, char mark) {
		this.name = name;
		this.mark = mark;
		isCurrentPlayer = true;
	}

	/**
	 * The first player object calls this method to start the game
	 * @throws IOException
	 */
	protected void play() {
		while (board.xWins() == false && board.oWins() == false && board.isFull() == false) {

			try {
				if (isCurrentPlayer) {
					socketOut.println(name + " it is your turn to make a move.");
					makeMove();
				}
				else{
					opponent.socketOut.println(opponent.name + " it is your turn to make a move.");
					opponent.makeMove();
				}

			} catch (Exception e) {
				e.printStackTrace();
			}

			displayToBothPlayers();
			switchPlayer();
		}

		if (board.oWins()) {
			socketOut.println("THE GAME IS OVER: " + opponent.name + " is the winner!");
			opponent.socketOut.println("THE GAME IS OVER: " + opponent.name + " is the winner!");
		} else if (board.xWins()) {
			socketOut.println("THE GAME IS OVER: " + name + " is the winner!");
			opponent.socketOut.println("THE GAME IS OVER: " + name + " is the winner!");
		}else {
			socketOut.println("THE GAME IS OVER: That was a tie!");
			opponent.socketOut.println("THE GAME IS OVER: That was a tie!");
		}
	}

	/**
	 * Prompts a player to pick a row and column on the board and adds their mark
	 * @throws IOException
	 */
	abstract protected void makeMove();

	/**
	 * Sets board to be visible to Player object
	 * @param board Board object
	 */
	public void setBoard(Board board) {
		this.board = board;
	}

	/**
	 * private method to switch player turn
	 */
	protected void switchPlayer() {
		if (isCurrentPlayer == true)
			isCurrentPlayer = false;
		else
			isCurrentPlayer = true;
	}


	/**
	 * Gets the player opponent
	 * @return Player object
	 */
	public Player getOpponent() {
		return opponent;
	}

	/**
	 * Sets the player opponent
	 * @param opponent Player object
	 */
	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public Board getBoard() {
		return board;
	}

	public PrintWriter getSocketOut() {
		return socketOut;
	}

	public BufferedReader getSocketIn() {
		return socketIn;
	}

	public void setSockets(BufferedReader socketIn, PrintWriter socketOut){
		this.socketIn = socketIn;
		this.socketOut = socketOut;
	}

	public void setSocketOut(PrintWriter socketOut) {
		this.socketOut = socketOut;
	}

	public void displayToBothPlayers(){
		board.setSocketOut(this.socketOut);
		board.display();
		board.setSocketOut(opponent.socketOut);
		board.display();
	}

}
