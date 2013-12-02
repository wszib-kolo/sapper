package sapper;

import sapper.Field.MineNumberWinLose;

public class BoardGenerator {
	private Field[][] fields; 
	private int sizeX, sizeY, numberOfMines;	
	
	public BoardGenerator(int sizeX, int sizeY, int numberOfMines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numberOfMines = numberOfMines;
		Field.setFieldToUncover(sizeY*sizeX-numberOfMines);
	}

	public Field[][] RandomizeMines() {
		int[][] mines = new int[sizeX][sizeY];
		int MinesSetted = 0;
		while (MinesSetted < numberOfMines) {
			int x = (int) (Math.random() * sizeX);
			int y = (int) (Math.random() * sizeY);
			if (mines[x][y] != 1) {
				mines[x][y] = 1;
				MinesSetted++;
			}
		}
		fillArray();
		setMines(mines);
		generateBoard();
		return fields;
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

