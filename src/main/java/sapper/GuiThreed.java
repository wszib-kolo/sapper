package sapper;

class GuiThreed implements Runnable {
	public void run() {
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.setVisible(true);
	}
}
