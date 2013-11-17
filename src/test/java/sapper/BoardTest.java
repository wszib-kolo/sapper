package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class BoardTest {
	@DataProvider(name = "FlagedFields")
	public Object[][] FlagedFields() {
		return new Object[][] { 
				{1,1,0,0,0,0,true},
				{2,2,0,0,1,1,false},
		};
	}
	
	@DataProvider(name = "FlagedCheckFields")
	public Object[][] FlagedCheckFields() {
		return new Object[][] { 
				{1,1,0,0,0,0,-1},
				{2,2,0,0,1,1,0},
		};
	}
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
	
	@Test(dataProvider = "FlagedFields")
	public void FlagedCoverFieldTest(int sizeX, int sizeY, int posFlagX, int posFlagY, int posCheckX, int posCheckY, boolean isFieldFlaged){
		Board board = new Board(sizeX, sizeY, 1);
		board.flagField(posFlagX, posFlagY, true);
		Assert.assertEquals(board.isFlaged(posCheckX, posCheckY), isFieldFlaged);
	}
	
	@Test(dataProvider = "FlagedFields")
	public void FlagedUncoverFieldTest(int sizeX, int sizeY, int posFlagX, int posFlagY, int posCheckX, int posCheckY, boolean isFieldFlaged){
		Board board = new Board(sizeX, sizeY, 1);
		board.checkField(posFlagX, posFlagY);
		board.flagField(posFlagX, posFlagY, true);
		Assert.assertEquals(board.isFlaged(posCheckX, posCheckY), false);
	}
	
	@Test(dataProvider = "FlagedCheckFields")
	public void FlagedCheckFieldTest(int sizeX, int sizeY, int posFlagX, int posFlagY, int posCheckX, int posCheckY, int ValueOfCheckReturn){
		Board board = new Board(sizeX, sizeY, 0);
		board.flagField(posFlagX, posFlagY, true);
		Assert.assertEquals(board.checkField(posCheckX, posCheckY), ValueOfCheckReturn);
	}
}
