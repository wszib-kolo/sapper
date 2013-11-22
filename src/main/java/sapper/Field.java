package sapper;

import java.io.Serializable;

public class Field implements Serializable {

	private static final long serialVersionUID = 1L;
	private boolean flag = false;
	private boolean mine;
	private int NearMinesNumber = 0;
	private boolean cover = true;

	public boolean getFlag() {
		return flag;
	}

	public void setFlag(boolean flagStatus) {
		flag = flagStatus;
	}

	public boolean isMine() {
		return mine;
	}

	public void increaseNearMinesNumber() {
		NearMinesNumber++;
	}

	public boolean isCover() {
		return cover;
	}

	public void setCover(boolean cover) {
		this.cover = cover;
	}

	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getNearMinesNumber() {
		return NearMinesNumber;
	}

	public void setNearMinesNumber(int nearMinesNumber) {
		this.NearMinesNumber = nearMinesNumber;
	}

}