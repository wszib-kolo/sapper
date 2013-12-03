package sapper;

import org.testng.Assert;
import org.testng.annotations.Test;

import sapper.Field.MineNumberWinLose;

public class FieldTest {

	@Test
	public void getFileStatusTest() {
		Field field = new Field();
		Assert.assertEquals(field.getFieldStatus(), MineNumberWinLose.OTHER);
	}

	@Test
	public void tryUncoverFieldTest() {
		Field field = new Field();
		Assert.assertEquals(field.tryUncoverField(), MineNumberWinLose.OTHER);
	}
}
