package controller;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JFrame;

import sapper.Board;
import sapper.Counter;

public class Save {
	private Board board;
	private Counter counter;

	public Save(Board board, Counter counter) {
		this.board = board;
		this.counter = counter;
	}

	public void saveToFile() {
		try {
			JFrame parentFrame = new JFrame();
			JFileChooser saveFileChooser = new JFileChooser();
			saveFileChooser.setDialogTitle("Write the name of saved game...");

			int userSelection = saveFileChooser.showSaveDialog(parentFrame);
			if (userSelection == JFileChooser.APPROVE_OPTION) {

				FileOutputStream fileStream = new FileOutputStream(saveFileChooser.getSelectedFile());
				ObjectOutputStream os = new ObjectOutputStream(fileStream);
				os.writeObject(board);
				os.writeObject(counter);
				os.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
