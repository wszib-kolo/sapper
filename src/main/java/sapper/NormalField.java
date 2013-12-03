package sapper;

import sapper.Field;

public class NormalField extends Field {
	private static final long serialVersionUID = 1L;

	public MineNumberWinLose tryUncoverField() {
		if (covered == false) {
			return MineNumberWinLose.OTHER;
		}
		if (flag == true) {
			return MineNumberWinLose.FLAG;
		}
		covered = false;
		fieldUncovered++;
		if (fieldToUncover == fieldUncovered) {
			return MineNumberWinLose.WIN;
		}

		return MineNumberWinLose.values()[NearMinesNumber];

	}

	public MineNumberWinLose getFieldStatus() {
		if (flag == true) {
			return MineNumberWinLose.FLAG;
		}
		return MineNumberWinLose.values()[NearMinesNumber];
	}

	public void setNearMinesNumber(int nearMinesNumber) {
		this.NearMinesNumber = nearMinesNumber;
	}
}
