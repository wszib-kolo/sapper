package sapper;

import java.io.Serializable;

import org.apache.log4j.Logger;

import sapper.MineNumberWinLose;

public class Board implements Serializable {
	private static transient Logger logger = Logger.getLogger(Board.class
			.getName());
	private static final long serialVersionUID = 1L;
	private Field[][] fields;

	public Board(Field[][] fields) {
		this.fields = fields;
	}

	public boolean isFlaged(int posX, int posY) {
		return fields[posX][posY].isFlagged();
	}

	public boolean changeFlagStatus(int x, int y) {
		if (!isWin()) {
			flagField(x, y, !isFlaged(x, y));
		}
		return isFlaged(x, y);
	}

	public MineNumberWinLose checkField(int posX, int posY) {
		MineNumberWinLose fieldStatus = fields[posX][posY].uncoverField();
		if ((isWin() == true)) {
			fieldStatus = MineNumberWinLose.WIN;
		}
		logger.debug(fieldStatus);
		return fieldStatus;
	}

	public MineNumberWinLose chekUncoverField(int posX, int posY) {
		if (fields[posX][posY].isCovered() == false
				|| fields[posX][posY].isFlagged() == true) {
			return fields[posX][posY].getFieldStatus();
		} else {
			return MineNumberWinLose.COVERED;
		}
	}

	public void flagField(int posX, int posY, boolean flagStatus) {
		fields[posX][posY].setFlag(flagStatus);
	}

	private boolean isWin() {
		for (int posX = 0; posX < fields.length; posX++) {
			for (int posY = 0; posY < fields[0].length; posY++) {
				if (!isMineField(posX, posY) && fields[posX][posY].isCovered()) {
					return false;
				}
			}
		}
		return true;
	}

	public boolean isMineField(int posX, int posY) {
		return (fields[posX][posY] instanceof MineField);
	}
}
