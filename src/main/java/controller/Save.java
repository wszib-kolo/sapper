package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import sapper.Board;
import sapper.BoardAndCounter;
import sapper.Counter;
import sapper.Options;

public class Save{

	private Board board;
	private Counter counter;
	private Options options;

	public Save(Board board, Counter counter, Options options) {
		this.board = board;
		this.counter = counter;
		this.options = options;
		
	}

	public void saveToFile() {
		BoardAndCounter boardAndCounter = new BoardAndCounter();
		boardAndCounter.setBoard(board);
		boardAndCounter.setCounter(counter);
		boardAndCounter.setOptions(options);

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