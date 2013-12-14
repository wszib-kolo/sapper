
package sapper;

import gui.HomeScreen;

public class GameOptions extends HomeScreen{

	private static final long serialVersionUID = 1L;
	
	public int sizeX = 0;
	public int sizeY = 0;
	public int numberOfMines = 0;
		

	public GameOptions(int sizeX, int sizeY, int numberOfMines) {
		this.sizeX = sizeX;
		this.sizeY = sizeY;
		this.numberOfMines = numberOfMines;	
	}
	
	public GameOptions(){
		initComponents();
	}

}
