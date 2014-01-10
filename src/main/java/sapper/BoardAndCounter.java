package sapper;

import java.io.Serializable;

public class BoardAndCounter implements Serializable {

	private static final long serialVersionUID = 1L;
	private Board board;
	private Counter counter;
	private Options options;

	public Options getOptions() {
		return options;
	}

	public void setOptions(Options options) {
		this.options = options;
	}

	public Board getBoard() {
		return board;
	}

	public Counter getCounter() {
		return counter;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public void setCounter(Counter counter) {
		this.counter = counter;
	}

}
