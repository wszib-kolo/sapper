package sapper;

public class Board {

	private Field[][] fields;
	private int numberUncovertedMines;
	private int numberOfMines;
	private int sizeX, sizeY;
	private boolean boom;

	public Board(int sizeX, int sizeY, int numberOfMines) {
		this.numberOfMines = numberOfMines;
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		fields = new Field[sizeX][sizeY];
		fillArray();
		fields[3][3].setMine(true);
		fields[1][1].setMine(true);
		generateBoard();
	}

	public int checkField(int posX, int posY) {
		if (fields[posX][posY].isCover() == true) {
			fields[posX][posY].setUnCover();
			boom = fields[posX][posY].isMine();
			numberUncovertedMines++;
			return fields[posX][posY].getNearMinesNumber();
		}
		return -1;
	}

	public boolean isWin() {
		if (numberUncovertedMines + numberOfMines == sizeX * sizeY) {
			return true;
		}
		return false;
	}

	public boolean isLoose() {
		return boom;
	}

	private Field[][] fillArray() {
		for (int i = 0; i < sizeX; i++) {
			for (int j = 0; j < sizeY; j++) {
				fields[i][j] = new Field();
			}
		}
		return fields;
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
				boolean isThereBoardDown = up < sizeY;
				boolean isThereBoardUp = down > -1;
				boolean isThereBoardLeft = left > -1;
				boolean isThereBoardRight = right < sizeX;
				
				if (fields[x][y].isMine() == true && sizeX > 1) {
					// W lewo
					if (isThereBoardLeft && fields[left][centerVertical].isMine() == false) {
						fields[left][centerVertical].nearMinesNumber += 1;
					}
					// Nad
					if (isThereBoardUp && fields[centerHorizontal][up].isMine() == false) {
						fields[centerHorizontal][up].nearMinesNumber += 1;
					}
					// Pod
					if (isThereBoardDown
							&& fields[centerHorizontal][down].isMine() == false) {
						fields[centerHorizontal][down].nearMinesNumber += 1;
					}
					// W prawo
					if (isThereBoardRight
							&& fields[right][centerVertical].isMine() == false) {
						fields[right][centerVertical].nearMinesNumber += 1;
					}
					// W górę i w lewo od miny
					if (isThereBoardLeft && isThereBoardUp
							&& fields[left][up].isMine() == false) {
						fields[left][up].nearMinesNumber += 1;
					}
					// W górę i w prawo od miny
					if (isThereBoardRight && isThereBoardUp
							&& fields[right][up].isMine() == false) {
						fields[right][up].nearMinesNumber += 1;
					}
					// W dół i w lewo od miny
					if (isThereBoardLeft && isThereBoardDown
							&& fields[left][down].isMine() == false) {
						fields[left][down].nearMinesNumber += 1;
					}
					// W dół i w prawo od miny
					if (isThereBoardRight && isThereBoardDown
							&& fields[right][down].isMine() == false) {
						fields[right][down].nearMinesNumber += 1;
					}
				}
			}
		}
	}
}