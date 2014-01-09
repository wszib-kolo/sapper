package sapper;

import java.io.Serializable;

public class Options implements Serializable{
	private static final long serialVersionUID = 1L;
	private int sizeX;
	private int sizeY;
	private int mines;
	
	public int getSizeX() {
		return sizeX;
	}
	public void setSizeX(int sizeX) {
		this.sizeX = sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public void setSizeY(int sizeY) {
		this.sizeY = sizeY;
	}
	public int getMines() {
		return mines;
	}
	public void setMines(int mines) {
		this.mines = mines;
	}
}
