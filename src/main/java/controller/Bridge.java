package controller;

import java.io.Serializable;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.BoardGenerator;
import sapper.Counter;
import sapper.MineNumberWinLose;

public class Bridge implements Serializable {

	private static final long serialVersionUID = 1L;

	private Board gameBoard;
	private Counter counter;
	private Save save;

	public boolean changeFieldFlagStatus(int x, int y) {
		return gameBoard.changeFlagStatus(x, y);
	}

	public MineNumberWinLose checkMine(int x, int y) {
		return gameBoard.checkField(x, y);
	}

	public int getGameTime() {
		return counter.getGameTime();
	}

	public Bridge() {
		BoardGenerator boardGen = new BoardGenerator();
		gameBoard = new Board(boardGen.randomizeMines());
		counter = new Counter();
	}

	public Bridge(BoardAndCounter boardAndCounter) {
		gameBoard = boardAndCounter.getBoard();
		counter = boardAndCounter.getCounter();
		save = null;
	}

	public void save() {
		save = new Save(gameBoard, counter);
		save.saveToFile();
	}

	public static BoardAndCounter load() {
		return Load.loadFromFile();
	}

	public Board getBoard() {
		return gameBoard;
	}

	public Counter getCounter() {
		return counter;
	}
}
