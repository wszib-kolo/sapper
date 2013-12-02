package sapper;

import sapper.Field;

public class MineField extends Field {

	private static final long serialVersionUID = 1L;

	public MineNumberWinLose tryUncoverField() {
		if (covered == false) {
			return MineNumberWinLose.OTHER;
		}
		if (flag == true) {
			return MineNumberWinLose.FLAGGEDMINE;
		}
		covered = false;
		return MineNumberWinLose.MINE;
	}

	public MineNumberWinLose getFieldStatus() {
		if (flag == true) {
			return MineNumberWinLose.FLAGGEDMINE;
		}
		return MineNumberWinLose.MINE;
	}
}
