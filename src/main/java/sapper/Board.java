package sapper;

import java.io.Serializable;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

import sapper.Field.MineNumberWinLose;

public class Board implements Serializable {
	private Logger logger = Logger.getLogger(Board.class.getName());
	private static final long serialVersionUID = 1L;
	private Field[][] fields;

	public Board(Field[][] fields) {
		BasicConfigurator.configure();
		this.fields = fields;
	}

	public boolean isFlaged(int posX, int posY) {
		return fields[posX][posY].isFlagged();
	}

	public boolean changeFlagStatus(int x, int y) {
		if (!isWin())
			flagField(x, y, !isFlaged(x, y));
		return isFlaged(x, y);
	}

	public MineNumberWinLose checkField(int posX, int posY) {
		MineNumberWinLose fieldStatus = fields[posX][posY].uncoverField();
		if ((isWin() == true)) {
			logger.debug(MineNumberWinLose.WIN);
			return MineNumberWinLose.WIN;
		}
		if ((fieldStatus == MineNumberWinLose.FLAG)
				&& (fields[posX][posY] instanceof MineField)) {
			logger.debug(MineNumberWinLose.FLAGGEDMINE);
			return MineNumberWinLose.FLAGGEDMINE;
		}
		logger.debug(fieldStatus);
		return fieldStatus;
	}

	private void flagField(int posX, int posY, boolean flagStatus) {
		fields[posX][posY].setFlag(flagStatus);
	}

	private boolean isWin() {
		for (int posX = 0; posX < fields.length; posX++) {
			for (int posY = 0; posY < fields[0].length; posY++) {
				if (!(fields[posX][posY] instanceof MineField)
						&& fields[posX][posY].isCovered()) {
					return false;
				}
			}
		}
		return true;
	}
}
