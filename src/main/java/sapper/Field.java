package sapper;

import java.io.Serializable;

public class Field implements Serializable {

	public enum MineNumberWinLose {
		ZERO, ONE, TWO, THREE, FOUR, FIVE, SIX, SEVEN, EIGHT, NINE, WIN, MINE, FLAG, FLAGGEDMINE, OTHER;
	}

	private static final long serialVersionUID = 1L;
	
	protected boolean flag;
	public int NearMinesNumber = 0;
	protected boolean covered = true;
	static protected int fieldToUncover, fieldUncovered=0;

	static void setFieldToUncover(int NumberOfFieldWithoutMines){
		fieldToUncover=NumberOfFieldWithoutMines;
	}
	public Field(){
		fieldUncovered=0;
	}
	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flagStatus) {
		if (covered == true) {
			flag = flagStatus;
		}
	}

	public void increaseNearMinesNumber() {
		NearMinesNumber++;
	}

	public boolean isCover() {
		return covered;
	}

	public void setCover(boolean cover) {
		this.covered = cover;
	}

	public MineNumberWinLose tryUncoverField() {
		return MineNumberWinLose.OTHER;
	}

	public MineNumberWinLose getFieldStatus() {
		return MineNumberWinLose.OTHER;
	}

}