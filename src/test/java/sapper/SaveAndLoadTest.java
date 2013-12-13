package sapper;

import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class SaveAndLoadTest {
 
	@Test
  public void loadedBoardEqualsSavedBoard() {
		Bridge bridgeInput = new Bridge(5,5,5);
  	Board boardInput = bridgeInput.getBoard();
  	Counter counterInput = bridgeInput.getCounter();
  	bridgeInput.save();
  	
  	BoardAndCounter boardAndCounterOutput = null;
  	Board boardOutput = null;
  	Counter counterOutput = null;
  	
  	boardAndCounterOutput = Bridge.load();
  	counterOutput = boardAndCounterOutput.getCounter();
  	boardOutput = boardAndCounterOutput.getBoard();
  	
  	assertSame(boardInput, boardOutput);
  	assertSame(counterInput, counterOutput);
  }
}
