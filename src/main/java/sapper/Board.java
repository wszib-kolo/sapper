package sapper;

import java.io.Serializable;
import sapper.Field.MineNumberWinLose;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	private Field[][] fields;
	private int sizeX, sizeY;

	public Board(int sizeX, int sizeY, int numberOfMines, int[][] mines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		fillArray();
		setMines(mines);
		generateBoard();
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
		MineNumberWinLose status = fields[posX][posY].tryUncoverField();
		if (status != MineNumberWinLose.MINE) {
			if (isWin() == true) {
				return MineNumberWinLose.WIN;
			}
		}
		return status;
	}

	private boolean isWin() {
		boolean win = true;
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				if (fields[x][y].getClass() != MineField.class	&& fields[x][y].isCover() == true) {
					win = false;
					break;
				}
			}
			if (win == false) {
				break;
			}
		}
		return win;
	}

	private void fillArray() {
		fields = new Field[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				fields[i][j] = new NormalField();
			}
		}
	}

	private void setMines(int[][] mines) {
		for (int x = 0; x < mines.length; x++) {
			for (int y = 0; y < mines[0].length; y++) {
				if (mines[x][y] == 1) {
					fields[x][y] = new MineField();
				}
			}
		}
	}

	private void generateBoard() {
		for (int x = 0; x < sizeX; x++) {
			for (int y = 0; y < sizeY; y++) {
				int centerHorizontal = x;
				int centerVertical = y;
				int down = y - 1;
				int up = y + 1;
				int left = x - 1;
				int right = x + 1;
				boolean isThereBoardUp = (up < sizeY);
				boolean isThereBoardDown = (down > -1);
				boolean isThereBoardLeft = (left > -1);
				boolean isThereBoardRight = (right < sizeX);

				if (fields[centerHorizontal][centerVertical].getFieldStatus() == MineNumberWinLose.MINE) {
					if (isThereBoardLeft == true) {
						fields[left][centerVertical].increaseNearMinesNumber();
					}
					if (isThereBoardUp == true) {
						fields[centerHorizontal][up].increaseNearMinesNumber();
					}
					if (isThereBoardDown == true) {
						fields[centerHorizontal][down]
								.increaseNearMinesNumber();
					}
					if (isThereBoardRight == true) {
						fields[right][centerVertical].increaseNearMinesNumber();
					}
					if (isThereBoardLeft == true && isThereBoardUp == true) {
						fields[left][up].increaseNearMinesNumber();
					}
					if (isThereBoardRight == true && isThereBoardUp == true) {
						fields[right][up].increaseNearMinesNumber();
					}
					if (isThereBoardLeft == true && isThereBoardDown == true) {
						fields[left][down].increaseNearMinesNumber();
					}
					if (isThereBoardRight == true && isThereBoardDown == true) {
						fields[right][down].increaseNearMinesNumber();
					}
				}
			}
		}
	}
}
