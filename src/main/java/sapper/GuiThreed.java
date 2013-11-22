package sapper;

class GuiThreed implements Runnable {

	public void run() {
		SapperGui saper = new SapperGui();
		saper.setVisible(true);
	}
}
