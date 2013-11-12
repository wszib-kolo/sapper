package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoardTest {
	@DataProvider(name = "FieldWithOrWithoutMinesTest")
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
			},
			
			{ 1, 1, 1,
				new int[][] {{0}}, 
				new int[][] {{0}}
			}
		};
	}
	@Test(dataProvider = "FieldWithOrWithoutMinesTest")
	public void vFieldWithOrWithoutMinesTest(int sizeX, int sizeY, int numberOfMines, int[][] mines, int[][] fields) {
		Board board = new Board(sizeX, sizeY, numberOfMines,mines);
		for(int x=0;x<sizeX;x++){
			for(int y=0;y<sizeY;y++){
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
	public void TwoToOneWinTest() {
		Board board = new Board(2, 1, 1, new int[][]{{1,0}});
		Assert.assertEquals(board.isWin(), false);
		board.checkField(1, 0);
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
