package sapper;

import java.io.Serializable;

public class Bridge implements Serializable {

	private static final long serialVersionUID = 1L;

	public enum MineNumberWinLose {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, WIN, MINE, FLAG, OTHER;
	}
	
	MineNumberWinLose status;
	Board gameBoard;
	Save save;
	Load load;

	public boolean changeFieldFlagStatus(int x, int y) {
		if (gameBoard.isFlaged(x, y) == true) {
			gameBoard.flagField(x, y, false);
			return gameBoard.isFlaged(x, y);
		} else {
			gameBoard.flagField(x, y, true);
			return gameBoard.isFlaged(x, y);
		}
	}

	public MineNumberWinLose checkMine(int x, int y) {
		if (gameBoard.isFlaged(x, y) == true) {
			return MineNumberWinLose.FLAG;
		}
		int minesNumber = gameBoard.checkField(x, y);
		if (gameBoard.isLoose()) {
			return MineNumberWinLose.MINE;
		}
		if (gameBoard.isWin() == true) {
			return MineNumberWinLose.WIN;
		}
		if (minesNumber >= 0) {
			return MineNumberWinLose.values()[minesNumber];
		} else
			return MineNumberWinLose.OTHER;

	}

	public int getGameTime() {
		return gameBoard.getGameTime();
	}
	
	public Bridge(int x, int y, int minesNumber) {
		gameBoard = new Board(x, y, minesNumber);
		save = new Save(gameBoard);
		load = new Load();
	}

	public Bridge(int x, int y, int minesNumber, int[][] mines) {
		gameBoard = new Board(x, y, minesNumber, mines);
		save = new Save(gameBoard);
		load = new Load();
	}

	// Constructor for loading game from file
	public void save(){
		save.saveToFile();
	}
	
	public void load(){
		gameBoard=load.loadFromFile();
	}
}
