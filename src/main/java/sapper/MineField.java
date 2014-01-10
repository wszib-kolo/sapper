package sapper;

import java.io.Serializable;

import sapper.Field;

public class MineField extends Field implements Serializable{

	private static final long serialVersionUID = 1L;

    public MineNumberWinLose getFieldStatus() {
        if (isFlagged() == true) {
            return MineNumberWinLose.FLAGGEDMINE;
        }
        return MineNumberWinLose.MINE;
    }
}