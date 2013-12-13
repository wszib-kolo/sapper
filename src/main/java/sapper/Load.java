package sapper;

import java.io.FileInputStream;
import java.io.ObjectInputStream;

public class Load{

	public static BoardAndCounter loadFromFile() {
		BoardAndCounter boardAndCounter = null;
		try {
			FileInputStream fileStream = new FileInputStream("MySapper.ser");
			ObjectInputStream is = new ObjectInputStream(fileStream);
			boardAndCounter = (BoardAndCounter) is.readObject();
			is.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return boardAndCounter;
	}
}
