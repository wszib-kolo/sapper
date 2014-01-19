package sapper;

import sapper.MineNumberWinLose;

public class BoardGenerator {
	private Field[][] fields; 
	private Options options;	
	
	public BoardGenerator(Options options) {
		this.options=options;
	}

	public Field[][] randomizeMines() {
		int[][] mines = new int[options.getSizeX()][options.getSizeY()];
		int minesSet = 0;
		while (minesSet < options.getMines()) {
			int x = (int) (Math.random() * options.getSizeX());
			int y = (int) (Math.random() * options.getSizeY());
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
		fields = new Field[options.getSizeX()][options.getSizeY()];
		for (int i = 0; i < options.getSizeX(); i++) {
			for (int j = 0; j < options.getSizeY(); j++) {
				createNormalField(i, j);
			}
		}
	}

	private void setMines(int[][] mines) {
		for (int x = 0; x < mines.length; x++) {
			for (int y = 0; y < mines[0].length; y++) {
				if (mines[x][y] == 1) {
					createMineField(x, y);
				}
			}
		}
	}

	private void generateBoard() {
		for (int x = 0; x < options.getSizeX(); x++) {
			for (int y = 0; y < options.getSizeY(); y++) {
				int centerHorizontal = x;
				int centerVertical = y;
				int down = y - 1;
				int up = y + 1;
				int left = x - 1;
				int right = x + 1;
				boolean isThereBoardUp = (up < options.getSizeY());
				boolean isThereBoardDown = (down > -1);
				boolean isThereBoardLeft = (left > -1);
				boolean isThereBoardRight = (right < options.getSizeX());

				if (isFieldMine(centerHorizontal, centerVertical)) {
					if (isThereBoardLeft == true) {
						increaseFieldMineNumber(left, centerVertical);
					}
					if (isThereBoardUp == true) {
						increaseFieldMineNumber(centerHorizontal, up);
					}
					if (isThereBoardDown == true) {
						increaseFieldMineNumber(centerHorizontal, down);
					}
					if (isThereBoardRight == true) {
						increaseFieldMineNumber(right, centerVertical);
					}
					if (isThereBoardLeft == true && isThereBoardUp == true) {
						increaseFieldMineNumber(left, up);
					}
					if (isThereBoardRight == true && isThereBoardUp == true) {
						increaseFieldMineNumber(right, up);
					}
					if (isThereBoardLeft == true && isThereBoardDown == true) {
						increaseFieldMineNumber(left, down);
					}
					if (isThereBoardRight == true && isThereBoardDown == true) {
						increaseFieldMineNumber(right, down);
					}
				}
			}
		}
	}
	
	public void createNormalField(int x, int y){
		fields[x][y] = new NormalField();
	}
	
	public void createMineField(int x, int y){
		fields[x][y] = new MineField();
	}
	
	public boolean isFieldMine(int x, int y){
		return fields[x][y].getFieldStatus() == MineNumberWinLose.MINE;
	}
	
	public void increaseFieldMineNumber(int x, int y){
		fields[x][y].increaseNearMinesNumber();
	}
}

