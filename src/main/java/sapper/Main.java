package sapper;

public class Main {
	private static Thread gui;

	public static void main(String[] args) {
		gui = new Thread(new GuiThreed(), "Gui Thread");
		gui.start();
	}
}
