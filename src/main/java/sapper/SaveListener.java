package sapper;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class SaveListener implements ActionListener {

	Board board;

	public void actionPerformed(ActionEvent e) {
		try {			
			FileOutputStream fileStream = new FileOutputStream("MySapper.ser");
			ObjectOutputStream os = new ObjectOutputStream(fileStream);
			os.writeObject(board);
			os.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

}
