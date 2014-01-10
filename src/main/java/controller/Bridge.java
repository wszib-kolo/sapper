package controller;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.BoardGenerator;
import sapper.Counter;
import sapper.MineNumberWinLose;
import sapper.Options;

public class Bridge {

	private Board gameBoard;
	private Counter counter;
	private Save save;
	private Options options;

	public boolean changeFieldFlagStatus(int x, int y) {
		return gameBoard.changeFlagStatus(x, y);
	}

	public MineNumberWinLose checkMine(int x, int y) {
		return gameBoard.checkField(x, y);
	}

	public MineNumberWinLose checkMineWithoutUncover(int x, int y) {
		return gameBoard.chekUncoverField(x, y);
	}

	public Bridge(int x, int y, int minesNumber) {
		options = new Options();
		options.setMines(minesNumber);
		options.setSizeX(x);
		options.setSizeY(y);
		BoardGenerator boardGen = new BoardGenerator(x, y, minesNumber);
		gameBoard = new Board(boardGen.randomizeMines());
		counter = new Counter();
	}

	public Bridge(BoardAndCounter boardAndCounter) {
		gameBoard = boardAndCounter.getBoard();
		counter = boardAndCounter.getCounter();
		options = boardAndCounter.getOptions();
		save = null;
	}

	public void save() {
		counter.SaveTimeoffset();
		save = new Save(gameBoard, counter, options);
		save.saveToFile();
	}

	public void load() {
		BoardAndCounter loadedData = Load.loadFromFile();
		if (loadedData != null) {
			gameBoard = loadedData.getBoard();
			counter = loadedData.getCounter();
			counter.setTimeStart();
			options = loadedData.getOptions();
		}
	}

	public Board getBoard() {
		return gameBoard;
	}

	public Counter getCounter() {
		return counter;
	}

	public int getSizeX() {
		return options.getSizeX();
	}

	public int getSizeY() {
		return options.getSizeY();
	}

	public int getMines() {
		return options.getMines();
	}

	public int getGameTime() {
		return counter.getGameTime();
	}
}
