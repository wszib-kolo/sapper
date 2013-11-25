package sapper;

import java.io.Serializable;

public class Board implements Serializable {

	private static final long serialVersionUID = 1L;
	private Field[][] fields;
	private int numberUncoveredMines;
	private int numberOfMines;
	private int sizeX, sizeY;
	private boolean boom = false;
	public long timeStart;

	public Board(int sizeX, int sizeY, int numberOfMines) {
		this.numberOfMines = numberOfMines;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		fillArray();
		int[][] mines = RandomMines();
		setMines(mines);
		setTimeStart();
		generateBoard();
	}

	public Board(int sizeX, int sizeY, int numberOfMines, int[][] mines) {
		this.numberOfMines = numberOfMines;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		fillArray();
		setMines(mines);
		setTimeStart();
		generateBoard();
	}

	public void flagField(int posX, int posY, boolean flagStatus) {
		if (fields[posX][posY].isCover() == true) {
			fields[posX][posY].setFlag(flagStatus);
		}
	}

	public boolean isFlaged(int posX, int posY) {
		return fields[posX][posY].getFlag();
	}

	public int checkField(int posX, int posY) {
		if (isFlaged(posX, posY) == true) {
			return -1;
		}
		boom = fields[posX][posY].isMine();
		if (fields[posX][posY].isCover() == true) {
			fields[posX][posY].setCover(false);
			numberUncoveredMines++;
			return fields[posX][posY].getNearMinesNumber();
		}
		return -1;
	}

	public boolean isWin() {
		if (numberUncoveredMines + numberOfMines == sizeX * sizeY) {
			return true;
		}
		return false;
	}

	public boolean isLoose() {
		return boom;
	}

	private void fillArray() {
		fields = new Field[sizeX][sizeY];
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				fields[i][j] = new Field();
			}
		}
	}

	private int[][] RandomMines() {
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
		return mines;
	}

	private void setMines(int[][] mines) {
		for (int x = 0; x < mines.length; x++) {
			for (int y = 0; y < mines[0].length; y++) {
				if (mines[x][y] == 1) {
					fields[x][y].setMine(true);
				}
			}
		}
	}
	
	private void setTimeStart() {		
		this.timeStart = System.currentTimeMillis();
	}
	
	public int getGameTime() {
		int gameTime = (int) ((System.currentTimeMillis() - timeStart)/1000);
		return gameTime;
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

				if (fields[centerHorizontal][centerVertical].isMine() == true) {
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
