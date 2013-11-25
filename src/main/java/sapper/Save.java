package sapper;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class Save{
	private Board board;
	public Save(Board board){
		this.board=board;
	}

	public void saveToFile(){
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
