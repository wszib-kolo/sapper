package sapper;

import org.testng.annotations.Test;
import static org.mockito.Mockito.*;

public class BoardGeneratorTest {
  @Test
  public void CreateBoardWithOneMineTest() {
	  BoardGenerator generator = spy(new BoardGenerator(1,1,1));
	  generator.randomizeMines();
	  verify(generator,times(1)).createMineField(0, 0);
  }
  @Test
  public void CreateBoardWithZeroMineTest() {
	  BoardGenerator generator = spy(new BoardGenerator(1,1,0));
	  generator.randomizeMines();
	  verify(generator,times(1)).createNormalField(0, 0);
  }  
  @Test
  public void CreateBoardWithNineMinesTest() {
	  BoardGenerator generator = spy(new BoardGenerator(3,3,9));
	  generator.randomizeMines();
	  verify(generator,times(1)).isFieldMine(0, 0);
	  verify(generator,times(1)).isFieldMine(1, 0);
	  verify(generator,times(1)).isFieldMine(2, 0);
	  verify(generator,times(1)).isFieldMine(1, 0);
	  verify(generator,times(1)).isFieldMine(1, 1);
	  verify(generator,times(1)).isFieldMine(1, 2);
	  verify(generator,times(1)).isFieldMine(2, 0);
	  verify(generator,times(1)).isFieldMine(2, 1);
	  verify(generator,times(1)).isFieldMine(2, 2);
  }  
  @Test
  public void CreateBoardWithFourMineNearMineNumberTest() {
	  BoardGenerator generator = spy(new BoardGenerator(2,2,4));
	  generator.randomizeMines();
	  verify(generator,times(3)).increaseFieldMineNumber(0, 0);
	  verify(generator,times(3)).increaseFieldMineNumber(1, 0);
	  verify(generator,times(3)).increaseFieldMineNumber(0, 1);
	  verify(generator,times(3)).increaseFieldMineNumber(1, 1);
  }  
}
