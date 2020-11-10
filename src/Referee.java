import java.io.IOException;

/**
 * Provides methods to instantiate a tic-tac-toe game 
 * @author David O. Laditan
 *
 */
public class Referee {

	private Player xPlayer;
	private Player oPlayer;
	private Board board;
	
	/**
	 * Sets the two players of the game with their respective marks, displays the board and starts the game
	 * @throws IOException
	 */
	public void runTheGame() throws IOException {
		xPlayer.setOpponent(oPlayer);
		oPlayer.setOpponent(xPlayer);
		board.setSocketOut(xPlayer.getSocketOut());
		board.display();
		board.setSocketOut(oPlayer.getSocketOut());
		board.display();
		xPlayer.play();
	}
	
	/**
	 * Sets the X player in the game
	 * @param xPlayer Player object 
	 */
	public void setxPlayer(Player xPlayer) {
		this.xPlayer = xPlayer;
	}
	
	/**
	 * Sets the O player in the game 
	 * @param oPlayer Player object 
	 */
	public void setoPlayer(Player oPlayer) {
		this.oPlayer = oPlayer;
	}
	
	/**
	 * Sets the board of the game 
	 * @param board Board object
	 */
	public void setBoard(Board board) {
		this.board = board;
	}
	
	
}
