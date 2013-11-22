package sapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class LoadListener implements ActionListener {

	Board board;
	
	public void actionPerformed(ActionEvent event) {
		try {
			FileInputStream fileStream = new FileInputStream("MySapper.ser");
			ObjectInputStream is = new ObjectInputStream(fileStream);
			board = (Board) is.readObject();	
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
