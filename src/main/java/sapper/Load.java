package sapper;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load{

	public Board loadFromFile() {
		Board board = null;
		try {
			FileInputStream fileStream = new FileInputStream("MySapper.ser");
			ObjectInputStream is = new ObjectInputStream(fileStream);
			board = (Board) is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return board;
	}
}
