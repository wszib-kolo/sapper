package controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.filechooser.FileNameExtensionFilter;

import sapper.BoardAndCounter;

public class Load {

	public static BoardAndCounter loadFromFile() {
		BoardAndCounter boardAndCounter = null;
		try {
			FileNameExtensionFilter filter = new FileNameExtensionFilter(
					"Saper saved games", "ser");
			JFrame parentFrame = new JFrame();
			JFileChooser loadFileChooser = new JFileChooser();
			loadFileChooser.addChoosableFileFilter(filter);
			loadFileChooser
					.setDialogTitle("Wybierz plik z grą, który chcesz wczytać.");
			int userSelection = loadFileChooser.showOpenDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {
				File file = loadFileChooser.getSelectedFile();
				FileInputStream fileStream = new FileInputStream(file);
				ObjectInputStream is = new ObjectInputStream(fileStream);
				boardAndCounter = (BoardAndCounter) is.readObject();
				is.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardAndCounter;
	}
}
