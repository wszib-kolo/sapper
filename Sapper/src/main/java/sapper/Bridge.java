package sapper;

public class Bridge {
	public enum MineNumberWinLose {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, WIN, LOSE, OTHER;
	}

	MineNumberWinLose status;
	Board gameBoard;
	public MineNumberWinLose checkMine(int x, int y) {
		int minesNumber=gameBoard.checkField(x, y);
		if(gameBoard.isWin()==true){
			return MineNumberWinLose.WIN;
		}
		if(gameBoard.isLoose()){
			return MineNumberWinLose.LOSE;
		}
		if(minesNumber>=0){
			System.out.println(minesNumber);
			return MineNumberWinLose.values()[minesNumber];
		}
		else return MineNumberWinLose.OTHER;
		
	}

	public Bridge(int x, int y, int mines) {
		gameBoard=new Board(x,y,mines);
	}
}
