package sapper;

import org.testng.Assert;
import org.testng.annotations.Test;

public class CounterTest {

	@Test
	public void getGameTime() {
		Counter counter = new Counter();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		Assert.assertEquals(counter.getGameTime(), 1);
	}
}
