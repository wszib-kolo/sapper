package gui;

import javax.swing.JLabel;
import sapper.Bridge;

public class CounterGUI implements Runnable {
	private Bridge bridge;
	private JLabel timeCounter;
	private boolean stop = false;

	private String updateTimeCounter() {
		int timeOfGame = bridge.getGameTime();
		return String.valueOf(timeOfGame);
	}
	
	public CounterGUI(JLabel timeCounter, Bridge bridge) {
		this.bridge = bridge;
		this.timeCounter = timeCounter;
	}

	public void stop(){
		stop=true;
	}
	
	public void start(){
		 Thread counterThread = new Thread(this,"Counter Thread" );
		 counterThread.start();
	}

	public void run() {
		while (stop == false) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			timeCounter.setText("Czas gry: " + updateTimeCounter() + " sekund");
		}
	}
}
