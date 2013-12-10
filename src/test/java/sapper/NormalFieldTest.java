package sapper;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class NormalFieldTest {
	@DataProvider(name = "UncoverFieldData")
	public Object[][] UncoverFieldData() {
		return new Object[][] { { 1, MineNumberWinLose.ONE },
				{ 2, MineNumberWinLose.TWO }, { 3, MineNumberWinLose.THREE },
				{ 4, MineNumberWinLose.FOUR }, { 5, MineNumberWinLose.FIVE },
				{ 6, MineNumberWinLose.SIX }, { 7, MineNumberWinLose.SEVEN },
				{ 8, MineNumberWinLose.EIGHT }, { 9, MineNumberWinLose.NINE },
				{ 0, MineNumberWinLose.ZERO }, };
	}

	@Test
	public void getFieldStatus() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		Assert.assertEquals(field.getFieldStatus(), MineNumberWinLose.ONE);
	}

	@Test(dataProvider = "UncoverFieldData")
	public void UncoverFieldCoverTestWithCoverField(int mineNumber,
			MineNumberWinLose uncoverFieldResult) {
		Field field = new NormalField();
		for (int i = 0; i < mineNumber; i++) {
			field.increaseNearMinesNumber();
		}
		Assert.assertEquals(field.uncoverField(), uncoverFieldResult);
	}

	@Test
	public void UncoverFieldCoverTestWithFlaggedField() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		field.setFlag(true);
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.FLAG);
	}

	@Test
	public void UncoverFieldCoverTestWithUncoverField() {
		Field field = new NormalField();
		field.increaseNearMinesNumber();
		field.uncoverField();
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.OTHER);
	}
}
