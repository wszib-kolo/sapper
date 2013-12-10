package sapper;

import org.testng.Assert;
import org.testng.annotations.Test;

public class MineFieldTest {

	@Test
	public void getFieldStatus() {
		Field field = new MineField();
		Assert.assertEquals(field.getFieldStatus(), MineNumberWinLose.MINE);
	}

	public void tryUncoverFieldCoverTestWithCoverField() {
		Field field = new MineField();
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.MINE);
	}

	@Test
	public void tryUncoverFieldCoverTestWithFlaggedField() {
		Field field = new MineField();
		field.setFlag(true);
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.FLAG);
	}

	@Test
	public void tryUncoverFieldCoverTestWithUncoverField() {
		Field field = new MineField();
		field.uncoverField();
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.OTHER);
	}
}
