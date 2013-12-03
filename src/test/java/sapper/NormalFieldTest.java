package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import sapper.Field.MineNumberWinLose;

public class NormalFieldTest {
	@DataProvider(name = "UncoverFieldData")
	public Object[][] UncoverFieldData() {
		return new Object[][] { { 1, MineNumberWinLose.ONE },
				{ 2, MineNumberWinLose.TWO }, { 3, MineNumberWinLose.THREE },
				{ 4, MineNumberWinLose.FOUR }, { 5, MineNumberWinLose.FIVE},
				{ 6, MineNumberWinLose.SIX }, { 7, MineNumberWinLose.SEVEN },
				{ 8, MineNumberWinLose.EIGHT }, { 9, MineNumberWinLose.NINE },
				{ 0, MineNumberWinLose.ZERO}, };
	}

	@Test
	public void getFieldStatus() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		Assert.assertEquals(field.getFieldStatus(), MineNumberWinLose.ONE);
	}

	@Test(dataProvider = "UncoverFieldData")
	public void tryUncoverFieldCoverTestWithCoverField(int mineNumber,	MineNumberWinLose uncoverFieldResult) {
		Field field = new NormalField();
		for (int i = 0; i < mineNumber; i++) {
			field.increaseNearMinesNumber();
		}
		Assert.assertEquals(field.tryUncoverField(), uncoverFieldResult);
	}

	@Test
	public void tryUncoverFieldCoverTestWithFlaggedField() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		field.setFlag(true);
		Assert.assertEquals(field.tryUncoverField(), MineNumberWinLose.FLAG);
		field.setFlag(false);
		Assert.assertEquals(field.tryUncoverField(), MineNumberWinLose.ONE);	
	}

	@Test
	public void tryUncoverFieldCoverTestWithUncoverField() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		field.tryUncoverField();
		Assert.assertEquals(field.tryUncoverField(), MineNumberWinLose.OTHER);
	}
	@Test
	public void winTest() {
		Field field = new NormalField();
		Field.setFieldToUncover(1);
		Assert.assertEquals(field.tryUncoverField(), MineNumberWinLose.WIN);
	}
}
