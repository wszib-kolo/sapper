package sapper;

import java.io.Serializable;

import sapper.Field.MineNumberWinLose;

public class Bridge implements Serializable {

	private static final long serialVersionUID = 1L;
	
	MineNumberWinLose status;
	Board gameBoard;
	Counter counter;
	Save save;
	Load load;

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
		MineGenerator mineGen = new MineGenerator(x, y, minesNumber);
		gameBoard = new Board(x, y, minesNumber,mineGen.RandomizeMines());
		counter = new Counter();
		save = new Save(gameBoard);
		load = new Load();
	}
	
	public void save(){
		save.saveToFile();
	}
	
	public void load(){
		gameBoard=load.loadFromFile();
	}
}
