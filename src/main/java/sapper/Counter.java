package sapper;

import java.io.Serializable;

public class Counter implements Serializable {

	private static final long serialVersionUID = 1L;
	private long timeStart;
	private int timeOffset = 0;

	public Counter() {
		setTimeStart();
	}

	public void SaveTimeoffset() {
		timeOffset = (int) ((System.currentTimeMillis() - timeStart) / 1000);
	}

	public void setTimeStart() {
		this.timeStart = System.currentTimeMillis();
	}

	public int getGameTime() {
		return ((int) ((System.currentTimeMillis() - timeStart) / 1000))
				+ timeOffset;
	}
}
