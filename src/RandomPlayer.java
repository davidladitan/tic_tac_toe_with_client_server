
public class RandomPlayer extends Player {

	protected RandomGenerator randomGen;

	public RandomPlayer(String name, char mark) {
		super(name, mark);
		randomGen = new RandomGenerator();
	}




	@Override
	protected void makeMove() {
		while(true) {

			int row =  randomGen.discrete(0, 2);
			int column = randomGen.discrete(0, 2);

			if (0 <= row && row <=2 && 0 <= column && column <=2 && board.getMark(row, column) == Constants.SPACE_CHAR) {
				board.addMark(row, column, mark);
				return;
			}

			System.out.println("You can't play in that spot, pick an empty spot!");

		}

	}
}
