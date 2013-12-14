package sapper;

import gui.HomeScreen;

public class GameOptions extends HomeScreen {

	BoardGenerator boardGenerator = new BoardGenerator();

	private static final long serialVersionUID = 1L;

	public static int sizeX = 5;
	public static int sizeY = 5;
	public static int numberOfMines = 5;

	public GameOptions(int sizeX, int sizeY, int numberOfMines) {
		GameOptions.sizeX = sizeX;
		GameOptions.sizeY = sizeY;
		GameOptions.numberOfMines = numberOfMines;
		boardGenerator.randomizeMines();
	}

	public GameOptions() {
		initComponents();
	}

}
