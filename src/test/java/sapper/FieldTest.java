package sapper;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FieldTest {

	@Test
	public void getFileStatusTest(){
		Field field = new Field();
		Assert.assertEquals(field.getFieldStatus(), MineNumberWinLose.OTHER);
	}

	@Test
	public void UncoverFieldTest() {
		Field field = new Field();
		Assert.assertEquals(field.uncoverField(), MineNumberWinLose.OTHER);
	}
}
