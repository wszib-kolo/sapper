package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.Counter;

public class Save implements Serializable {

	private static final long serialVersionUID = 1L;
	private Board board;
	private Counter counter;

	public Save(Board board, Counter counter) {
		this.board = board;
		this.counter = counter;
	}

	public void saveToFile() {
		BoardAndCounter boardAndCounter = new BoardAndCounter();
		boardAndCounter.setBoard(board);
		boardAndCounter.setCounter(counter);

		try {
			JFrame parentFrame = new JFrame();
			JFileChooser saveFileChooser = new JFileChooser();
			saveFileChooser.setDialogTitle("Wpisz nazwę zapisywanej gry.");
			int userSelection = saveFileChooser.showSaveDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				FileOutputStream fileStream = new FileOutputStream(
						saveFileChooser.getSelectedFile() + ".ser");
				ObjectOutputStream os = new ObjectOutputStream(fileStream);

				os.writeObject(boardAndCounter);
				os.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}