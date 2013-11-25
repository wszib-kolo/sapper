package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sapper.Bridge.MineNumberWinLose;

public class BridgeTest {
	@DataProvider(name = "MinesSetsToTest")
	public Object[][] MinesSetsToTest() {
		return new Object[][] {
				{
						4,
						4,
						8,
						new int[][] { { 1, 1, 1, 0 }, { 1, 0, 1, 0 },
								{ 1, 1, 1, 0 }, { 0, 0, 0, 0 } },
						MineNumberWinLose.EIGHT, 1, 1 },
				{ 3, 3, 7,
						new int[][] { { 1, 1, 1 }, { 0, 0, 1 }, { 1, 1, 1 } },
						MineNumberWinLose.SEVEN, 1, 1 },
				{ 3, 3, 6,
						new int[][] { { 0, 0, 1 }, { 1, 0, 1 }, { 1, 1, 1 } },
						MineNumberWinLose.SIX, 1, 1 },
				{ 3, 3, 5,
						new int[][] { { 0, 0, 0 }, { 1, 0, 1 }, { 1, 1, 1 } },
						MineNumberWinLose.FIVE, 1, 1 },
				{ 3, 3, 4,
						new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 1, 1, 1 } },
						MineNumberWinLose.FOUR, 1, 1 },
				{ 3, 3, 3,
						new int[][] { { 1, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 } },
						MineNumberWinLose.THREE, 1, 1 },
				{ 3, 3, 2,
						new int[][] { { 0, 1, 1 }, { 0, 0, 0 }, { 0, 0, 0 } },
						MineNumberWinLose.TWO, 1, 1 },
				{ 3, 3, 1,
						new int[][] { { 0, 0, 0 }, { 1, 0, 0 }, { 0, 0, 0 } },
						MineNumberWinLose.ONE, 1, 1 },
				{ 3, 3, 0,
						new int[][] { { 0, 0, 0 }, { 0, 0, 0 }, { 0, 0, 0 } },
						MineNumberWinLose.ZERO, 1, 1 },
				{ 3, 3, 8,
						new int[][] { { 1, 1, 1 }, { 1, 0, 1 }, { 1, 1, 1 } },
						MineNumberWinLose.WIN, 1, 1 },
				{ 3, 3, 5,
						new int[][] { { 0, 0, 0 }, { 1, 0, 1 }, { 1, 1, 1 } },
						MineNumberWinLose.MINE, 2, 2 } };
	}

	@DataProvider(name = "FlagsSetsToTest")
	public Object[][] FlagsSetsToTest() {
		return new Object[][] { { 2, 2, 3, new int[][] { { 1, 1 }, { 1, 0 } },
				true, 1, 1 } };
	}

	@DataProvider(name = "FlagsSetsToCheckFieldTest")
	public Object[][] FlagsSetsToCheckFieldTest() {
		return new Object[][] {
				{ 2, 2, 3, new int[][] { { 1, 1 }, { 1, 0 } },
						MineNumberWinLose.FLAG, 1, 1, 1, 1 },
				{ 2, 2, 3, new int[][] { { 1, 1 }, { 1, 0 } },
						MineNumberWinLose.WIN, 0, 0, 1, 1 },
				{ 3, 3, 3,
						new int[][] { { 1, 1, 0 }, { 1, 0, 0 }, { 0, 0, 0 } },
						MineNumberWinLose.THREE, 0, 0, 1, 1 } };
	}

	@DataProvider(name = "FlagsSetsToFieldUncoverTest")
	public Object[][] FlagsSetsToFieldUcoverTest() {
		return new Object[][] { { 2, 2, 2, new int[][] { { 1, 1 }, { 0, 0 } },
				true, 1, 1 } };
	}

	@Test(dataProvider = "MinesSetsToTest")
	public void checkMineTestAllWithoutLose(int sizeX, int sizeY,
			int numberOfMines, int[][] mines,
			MineNumberWinLose expectedReturnValue, int clicX, int clicY) {
		Bridge bridge = new Bridge(sizeX, sizeY, numberOfMines, mines);
		Assert.assertEquals(bridge.checkMine(clicX, clicY), expectedReturnValue);
	}

	@Test(dataProvider = "FlagsSetsToTest")
	public void changeFieldFlagStatusTest(int sizeX, int sizeY,
			int numberOfMines, int[][] mines, boolean expectedReturnValue,
			int clicX, int clicY) {
		Bridge bridge = new Bridge(sizeX, sizeY, numberOfMines, mines);
		boolean existValue = bridge.changeFieldFlagStatus(clicX, clicY);
		Assert.assertEquals(existValue, expectedReturnValue);
	}

	@Test(dataProvider = "FlagsSetsToCheckFieldTest")
	public void chackMineFlagTest(int sizeX, int sizeY, int numberOfMines,
			int[][] mines, MineNumberWinLose expectedReturnValue, int clicX,
			int clicY, int checkX, int checkY) {
		Bridge bridge = new Bridge(sizeX, sizeY, numberOfMines, mines);
		bridge.changeFieldFlagStatus(clicX, clicY);
		MineNumberWinLose existValue = bridge.checkMine(checkX, checkY);
		Assert.assertEquals(existValue, expectedReturnValue);
	}

	@Test(dataProvider = "FlagsSetsToFieldUncoverTest")
	public void chackMineFlagUncoverFieldTest(int sizeX, int sizeY,
			int numberOfMines, int[][] mines, boolean expectedReturnValue,
			int clicX, int clicY) {
		Bridge bridge = new Bridge(sizeX, sizeY, numberOfMines, mines);
		bridge.checkMine(clicX, clicY);
		bridge.changeFieldFlagStatus(clicX, clicX);
		MineNumberWinLose existValue = bridge.checkMine(clicX, clicX);
		Assert.assertEquals(existValue, MineNumberWinLose.OTHER);
	}
}
