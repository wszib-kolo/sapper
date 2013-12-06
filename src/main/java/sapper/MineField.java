package sapper;

import sapper.Field;

public class MineField extends Field {

	private static final long serialVersionUID = 1L;

	public MineNumberWinLose getFieldStatus() {
		if (isFlagged() == true) {
			return MineNumberWinLose.FLAG;
		}
		return MineNumberWinLose.MINE;
	}
}