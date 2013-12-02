package sapper;

import java.io.Serializable;
import sapper.Field.MineNumberWinLose;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	private Field[][] fields;

	public Board(Field[][] fields) {
		this.fields=fields;
	}

	private void flagField(int posX, int posY, boolean flagStatus) {
		fields[posX][posY].setFlag(flagStatus);
	}

	public boolean isFlaged(int posX, int posY) {
		return fields[posX][posY].getFlag();
	}

	public boolean changeFlagStatus(int x, int y) {
		flagField(x, y, !isFlaged(x, y));
		return isFlaged(x, y);
	}
	
	public MineNumberWinLose checkField(int posX, int posY) {
		return fields[posX][posY].tryUncoverField();
	}

}
