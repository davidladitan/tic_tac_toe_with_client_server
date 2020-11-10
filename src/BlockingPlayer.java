/**
 *
 * @author David O. Laditan
 *
 */
public class BlockingPlayer extends RandomPlayer {

	char[][] testBoard;

	public BlockingPlayer(String name, char mark) {
		super(name, mark);

	}



	@Override
	protected void makeMove() {
		while(true) {


			int row =0;
			int column =0;

			for (int i = 0; i < 3; i++) {
				for(int j =0; j <3; j++) {
					if(testForBlocking(i, j, opponent.mark) == true && board.getMark(i, j) == Constants.SPACE_CHAR ) {
						board.addMark(i, j, mark);

						return;
					}
				}
			}


			row =  randomGen.discrete(0, 2);
			column = randomGen.discrete(0, 2);



			if (0 <= row && row <=2 && 0 <= column && column <=2 && board.getMark(row, column) == Constants.SPACE_CHAR) {
				board.addMark(row, column, mark);
				System.out.println("I'm here");
				return;
			}

			System.out.println("You can't play in that spot, pick an empty spot!");

		}

	}



	public boolean testForBlocking(int i, int j, char mark) {
		testBoard = board.getTestBoard();
		addMarkTest(i, j, mark);

		if (checkWinner(testBoard, mark) == 1 || checkWinner(testBoard, this.mark) == 1)
			return true;
		else
			return false;

	}



	public char[][] cloneArray(char[][] src) {
		int length = src.length;
		char[][] target = new char[length][src[0].length];
		for (int i = 0; i < length; i++) {
			System.arraycopy(src[i], 0, target[i], 0, src[i].length);
		}
		return target;
	}

	private void addMarkTest(int i, int j, char mark) {
		testBoard[i][j] = mark;
	}

	int checkWinner(char[][] theBoard, char mark) {
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

}
