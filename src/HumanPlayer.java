import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanPlayer extends Player{


	public HumanPlayer(String name, char mark) {
		super(name, mark);
		// TODO Auto-generated constructor stub
	}



	protected void makeMove() {

		int row =0;
		int column =0;
		while(true) {
			try {
//				BufferedReader stdin;
//				stdin = new BufferedReader(new InputStreamReader(System.in));
				socketOut.println(name + ", what row should your next " + mark + " be placed in?");
				row = Integer.parseInt(socketIn.readLine()) ;// send to server


				socketOut.println(name + ", what column should your next " + mark + " be placed in?");
				column = Integer.parseInt(socketIn.readLine()) ; //send to server
			}catch(Exception e) {
				e.getStackTrace();
			}


			if (0 <= row && row <=2 && 0 <= column && column <=2 && board.getMark(row, column) == Constants.SPACE_CHAR) {
				board.addMark(row, column, mark); // receive row and column as response from server
				return;
			}

			socketOut.println("You can't play in that spot, pick an empty spot!");

		}
	}
}