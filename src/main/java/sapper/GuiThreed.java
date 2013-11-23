package sapper;

class GuiThreed implements Runnable {
	@Override
	public void run() {
		HomeScreen homeScreen = new HomeScreen();
		homeScreen.setVisible(true);
	}
}
