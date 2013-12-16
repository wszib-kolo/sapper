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
	
	public Bridge(int x, int y, int minesNumber) {
		BoardGenerator boardGen = new BoardGenerator(x, y, minesNumber);
		gameBoard = new Board(boardGen.randomizeMines());
		counter = new Counter();
	}
	
	public void save(){
		save = new Save(gameBoard);
		save.saveToFile();
	}
	
	public static BoardAndCounter load(){
		return Load.loadFromFile();
	}
	
	public Board getBoard(){
		return gameBoard;
	}
	public Counter getCounter(){
		return counter;
	}
}
