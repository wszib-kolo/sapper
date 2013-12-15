package sapper;

import static org.mockito.Mockito.*;

import org.testng.Assert;
import org.testng.annotations.Test;

import sapper.Field;

public class BoardTest {
	@Test
	public void UncoverFieldZeroTest() {
		Field[][] field = new Field[1][1];
		field[0][0] = mock(Field.class);
		Board board = new Board(field);
		when(field[0][0].isCovered()).thenReturn(true);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.ZERO);
		when(field[0][0].isFlagged()).thenReturn(false);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.ZERO);
		Assert.assertEquals(board.checkField(0, 0), MineNumberWinLose.ZERO);
	}

	@Test
	public void UncoverFieldWinTest() {
		Field[][] field = new Field[1][1];
		field[0][0] = mock(Field.class);
		Board board = new Board(field);
		when(field[0][0].isCovered()).thenReturn(false);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.ZERO);
		when(field[0][0].isFlagged()).thenReturn(false);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.ZERO);
		Assert.assertEquals(board.checkField(0, 0), MineNumberWinLose.WIN);
	}

	@Test
	public void UncoverFieldMineTest() {
		Field[][] field = new Field[1][2];
		field[0][0] = mock(Field.class);
		field[0][1] = mock(Field.class);
		Board board = spy(new Board(field));

		when(field[0][0].isCovered()).thenReturn(false);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.MINE);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.MINE);

		when(field[0][1].isCovered()).thenReturn(true);
		when(field[0][1].getFieldStatus()).thenReturn(MineNumberWinLose.ONE);
		when(field[0][1].uncoverField()).thenReturn(MineNumberWinLose.ONE);

		when(board.isMineField(0, 0)).thenReturn(true);
		Assert.assertEquals(board.checkField(0, 0), MineNumberWinLose.MINE);
	}

	@Test
	public void UncoverFieldWinTwoForOneTest() {
		Field[][] field = new Field[1][2];
		field[0][0] = mock(Field.class);
		field[0][1] = mock(Field.class);
		Board board = spy(new Board(field));

		when(field[0][0].isCovered()).thenReturn(false);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.MINE);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.MINE);

		when(field[0][1].isCovered()).thenReturn(false);
		when(field[0][1].getFieldStatus()).thenReturn(MineNumberWinLose.ONE);
		when(field[0][1].uncoverField()).thenReturn(MineNumberWinLose.ONE);

		when(board.isMineField(0, 0)).thenReturn(true);
		Assert.assertEquals(board.checkField(0, 1), MineNumberWinLose.WIN);
	}

	@Test
	public void ChangeFlagStatusTest() {
		Field[][] field = new Field[1][1];
		field[0][0] = mock(Field.class);
		;
		Board board = spy(new Board(field));

		when(field[0][0].isCovered()).thenReturn(true);
		when(field[0][0].isFlagged()).thenReturn(true);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.ONE);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.ONE);

		when(board.isMineField(0, 0)).thenReturn(false);

		board.changeFlagStatus(0, 0);
		verify(board, times(1)).flagField(0, 0, false);
	}

	@Test
	public void ChangeFlagStatusWhenWinTest() {
		Field[][] field = new Field[1][1];
		field[0][0] = mock(Field.class);
		;
		Board board = spy(new Board(field));

		when(field[0][0].isCovered()).thenReturn(false);
		when(field[0][0].isFlagged()).thenReturn(true);
		when(field[0][0].getFieldStatus()).thenReturn(MineNumberWinLose.ONE);
		when(field[0][0].uncoverField()).thenReturn(MineNumberWinLose.ONE);

		when(board.isMineField(0, 0)).thenReturn(false);

		board.changeFlagStatus(0, 0);
		verify(board, times(0)).flagField(0, 0, false);
		verify(board, times(0)).flagField(0, 0, true);
	}

}
