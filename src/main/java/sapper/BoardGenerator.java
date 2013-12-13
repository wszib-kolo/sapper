package sapper;

import sapper.MineNumberWinLose;

public class BoardGenerator {

	private Field[][] fields;

	public Field[][] randomizeMines() {
		int[][] mines = new int[GameOptions.sizeX][GameOptions.sizeY];
		int minesSet = 0;
		while (minesSet < GameOptions.numberOfMines) {
			int x = (int) (Math.random() * GameOptions.sizeX);
			int y = (int) (Math.random() * GameOptions.sizeY);
			if (mines[x][y] != 1) {
				mines[x][y] = 1;
				minesSet++;
			}
		}
		fillArray();
		setMines(mines);
		generateBoard();
		return fields;
	}

	private void fillArray() {
		fields = new Field[GameOptions.sizeX][GameOptions.sizeY];
		for (int i = 0; i < GameOptions.sizeX; i++) {
			for (int j = 0; j < GameOptions.sizeY; j++) {
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
		for (int x = 0; x < GameOptions.sizeX; x++) {
			for (int y = 0; y < GameOptions.sizeY; y++) {
				int centerHorizontal = x;
				int centerVertical = y;
				int down = y - 1;
				int up = y + 1;
				int left = x - 1;
				int right = x + 1;
				boolean isThereBoardUp = (up < GameOptions.sizeY);
				boolean isThereBoardDown = (down > -1);
				boolean isThereBoardLeft = (left > -1);
				boolean isThereBoardRight = (right < GameOptions.sizeX);

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
