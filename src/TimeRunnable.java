public class TimeRunnable {

	private long time;
	private Runnable runnable;


	TimeRunnable(long time, Runnable runnable) {
		this.time = time;
		this.runnable = runnable;
	}

	public long getTime() {
		return time;
	}

	public Runnable getRunnable() {
		return runnable;
	}

}