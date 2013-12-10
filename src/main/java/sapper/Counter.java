package sapper;

public class Counter {
	private long timeStart;

	public Counter() {
		setTimeStart();
	}

	private void setTimeStart() {
		this.timeStart = System.currentTimeMillis();
	}

	public int getGameTime() {
		return (int) ((System.currentTimeMillis() - timeStart) / 1000);
	}
}
	