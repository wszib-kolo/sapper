package sapper;

public class MineGenerator {
	
	public MineGenerator(int sizeX, int sizeY, int numberOfMines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numberOfMines = numberOfMines;
	}

	private int sizeX, sizeY, numberOfMines;

	public int[][] RandomizeMines() {
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
}

