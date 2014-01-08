package controller;

import java.io.FileInputStream;
import java.io.ObjectInputStream;
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
			loadFileChooser.setFileSelectionMode(JFileChooser.FILES_ONLY);

			int userSelection = loadFileChooser.showOpenDialog(parentFrame);

			if (userSelection == JFileChooser.APPROVE_OPTION) {

				FileInputStream fileStream = new FileInputStream(
						loadFileChooser.getSelectedFile());
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
