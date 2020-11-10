
//Game.java
import java.io.*;

public class Game implements Constants, Runnable{

	private Board theBoard;
	private Referee theRef;
	private Player oPlayer;
	private Player xPlayer;

	/**
	 * creates a board for the game
	 */
	public Game(Player xPlayer, Player oPlayer ) {
		theBoard  = new Board();
		this.xPlayer = xPlayer;
		this.oPlayer = oPlayer;
	}

	/**
	 * calls the referee method runTheGame
	 * @param r refers to the appointed referee for the game
	 * @throws IOException
	 */
	public void appointReferee(Referee r) throws IOException {
		theRef = r;
		theRef.runTheGame();
	}


	@Override
	public void run() {
		xPlayer.getSocketOut().println("Game Started!");
		oPlayer.getSocketOut().println("Game Started!");
		xPlayer.setBoard(theBoard);
		oPlayer.setBoard(theBoard);
		theRef = new Referee();
		theRef.setBoard(theBoard);
		theRef.setoPlayer(oPlayer);
		theRef.setxPlayer(xPlayer);

		try {
			appointReferee(theRef);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
