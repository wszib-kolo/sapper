package sapper;

import java.io.Serializable;

public class Field implements Serializable {

	private static final long serialVersionUID = 1L;

	protected boolean flagged;
	public int nearMinesNumber = 0;
	protected boolean covered = true;

	public boolean isFlagged() {
		return flagged;
	}

	public void setFlag(boolean flagStatus) {
		if (covered == true) {
			flagged = flagStatus;
		}
	}

	public void increaseNearMinesNumber() {
		nearMinesNumber++;
	}

	public boolean isCovered() {
		return covered;
	}

	public void setCovered(boolean cover) {
		this.covered = cover;
	}

    public MineNumberWinLose uncoverField() {
        if (isCovered() == false) {
            return MineNumberWinLose.OTHER;
        }
        MineNumberWinLose fieldStatus = getFieldStatus();
        if (isFlagged() == true) {
            return fieldStatus;
        }
        covered = false;
        return fieldStatus;
    }

	public MineNumberWinLose getFieldStatus() {
		return MineNumberWinLose.OTHER;
	}

}