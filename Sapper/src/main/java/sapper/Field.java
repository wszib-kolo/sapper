package sapper;

public class Field {

	protected boolean mine;
	protected int nearMinesNumber = 0;
	private boolean cover = true;
	
	public boolean isMine() {
		return mine;
	}
	
	public boolean isCover(){
		return cover;
	}
	public void setUnCover(){
		cover=false;
	}
	
	public void setMine(boolean mine) {
		this.mine = mine;
	}

	public int getNearMinesNumber() {
		return nearMinesNumber;
	}

	public void setNearMinesNumber(int nearMinesNumber) {
		this.nearMinesNumber = nearMinesNumber;
	}

}