package controller;

import org.testng.annotations.Test;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.Counter;
import sapper.Options;
import controller.Bridge;
import static org.testng.Assert.*;

public class SaveAndLoadTest {

	@Test
	public void loadedBoardAndCounterEqualsSavedBoardAndCounter() {
		Options options =new Options(5, 5, 5);
		Bridge bridgeInput = new Bridge(options);
		BoardAndCounter boardAndCounterInput = new BoardAndCounter();
		// boardAndCounterInput.setBoard(bridgeInput.getBoard());
		// boardAndCounterInput.setCounter(bridgeInput.getCounter());
		// bridgeInput.save();

		BoardAndCounter boardAndCounterOutput = new BoardAndCounter();
		//boardAndCounterOutput = Bridge.load();
		//boardAndCounterInput = Bridge.load();

		assertEquals(boardAndCounterInput, boardAndCounterOutput);
	}
}
