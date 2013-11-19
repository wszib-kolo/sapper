package sapper;

class GuiThreed implements Runnable {
	@Override
	public void run() {
		SapperGui saper = new SapperGui();
		saper.setVisible(true);
	}
}