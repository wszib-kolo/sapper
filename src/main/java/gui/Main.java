package gui;

import org.apache.log4j.BasicConfigurator;

public class Main {
	private static Thread gui;

	public static void main(String[] args) {
		BasicConfigurator.configure();
		gui = new Thread(new GuiThreed(), "Gui Thread");
		gui.start();
	}
}
