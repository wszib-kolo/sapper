package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoardTest {

	@DataProvider(name = "OneToOneWithMines")
	public Object[][] OneToOneWithMines() {
		return new Object[][] { 
			{ 1, 1, 1,
			new int[][] {{0}}, 
			new int[][] {{0}}
			} 
		};
	}

	@Test(dataProvider = "OneToOneWithMines")
	public void verifyDataOneToOneWithMine(int sizeX, int sizeY, int numberOfMines, int[][] mines, int[][] fields) {
		Board board = new Board(sizeX, sizeY, numberOfMines,mines);
		Assert.assertEquals(board.checkField(0, 0), fields[0][0]);
	}
	
	@DataProvider(name = "FiveToFiveWithMines")
	public Object[][] FiveToFiveWithMines() {
		return new Object[][] { 
			{ 5, 5, 5,
			new int[][] {
					{0,0,0,0,1},
					{0,0,1,0,0},
					{0,0,0,1,0},
					{0,0,1,0,0},
					{0,1,0,0,0}
			}, 
			new int[][] {
					{0,1,1,2,0},
					{0,1,1,3,2},
					{0,2,3,2,1},
					{1,2,2,2,1},
					{1,1,2,1,0}
			}
			} 
		};
	}
	@Test(dataProvider = "FiveToFiveWithMines")
	public void verifyDataFiveToFiveWithFiveMines(int sizeX, int sizeY, int numberOfMines, int[][] mines, int[][] fields) {
		Board board = new Board(sizeX, sizeY, numberOfMines,mines);
		for(int x=0;x<sizeX;x++){
			for(int y=0;y<sizeY;y++){System.out.println(x+" "+y);
				Assert.assertEquals(board.checkField(x, y), fields[x][y]);	
			}
		}
	}

	@Test
	public void OneToOneWinTest() {
		Board board = new Board(1, 1, 1);
		Assert.assertEquals(board.isWin(), true);
	}
	@Test
	public void OneToOneLooseTest() {
		Board board = new Board(1, 1, 1);
		Assert.assertEquals(board.isLoose(), false);
		board.checkField(0, 0);
		Assert.assertEquals(board.isLoose(), true);
	}
}
