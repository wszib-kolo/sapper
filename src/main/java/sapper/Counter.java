package sapper;

import javax.swing.JLabel;

public class Counter implements Runnable {
	private Bridge bridge;
	private JLabel timeCounter;
	private boolean stop=false;

	public Counter(JLabel timeCounter, Bridge bridge) {
		this.bridge = bridge;
		this.timeCounter = timeCounter;
	}
	@Override
	public void run() {
		while (stop==false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeCounter.setText("Czas gry: " + updateTimeCounter() + " sekund");
		}
	}
	private String updateTimeCounter() {
		int timeOfGame = bridge.getGameTime();
		return String.valueOf(timeOfGame);
	}
}
