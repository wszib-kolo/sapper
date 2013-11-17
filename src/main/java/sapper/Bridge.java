package sapper;

public class Bridge {
	public enum MineNumberWinLose {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, WIN, LOSE, OTHER, FLAG;
	}

	MineNumberWinLose status;
	Board gameBoard;
	
	public boolean changeFieldFlagStatus(int x, int y){
		return false;
	}
	public MineNumberWinLose checkMine(int x, int y) {
		int minesNumber=gameBoard.checkField(x, y);
		if(gameBoard.isLoose()){
			return MineNumberWinLose.LOSE;
		}
		if(gameBoard.isWin()==true){
			return MineNumberWinLose.WIN;
		}
		if(minesNumber>=0){
			return MineNumberWinLose.values()[minesNumber];
		}
		else return MineNumberWinLose.OTHER;
		
	}

	public Bridge(int x, int y, int minesNumber) {
		gameBoard=new Board(x,y,minesNumber);
	}
	
	public Bridge(int x, int y, int minesNumber,int [][] mines){
		gameBoard=new Board(x,y,minesNumber,mines);
	}
}
