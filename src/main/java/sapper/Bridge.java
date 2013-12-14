package sapper;

import java.io.Serializable;

import sapper.MineNumberWinLose;

public class Bridge implements Serializable {

	private static final long serialVersionUID = 1L;

	private Board gameBoard;
	private Counter counter;
	private Save save;
	private Load load;
	private GameOptions gameOptions;
	
	public boolean changeFieldFlagStatus(int x, int y) {
		return gameBoard.changeFlagStatus(x, y);
	}

	public MineNumberWinLose checkMine(int x, int y) {
		return gameBoard.checkField(x, y);
	}

	public int getGameTime() {
		return counter.getGameTime();
	}

	public Bridge(int x, int y, int minesNumber) {
		BoardGenerator boardGen = new BoardGenerator();
		//gameOptions = new GameOptions(x, y, minesNumber);
		
		gameBoard = new Board(boardGen.randomizeMines());
		counter = new Counter();
		save = new Save(gameBoard);
		load = new Load();
	}

	public void save() {
		save.saveToFile();
	}

	public void load() {
		gameBoard = load.loadFromFile();
	}
}
