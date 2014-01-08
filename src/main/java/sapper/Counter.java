package sapper;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
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
