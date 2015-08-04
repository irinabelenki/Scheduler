public class TimeRunnable implements Comparable<TimeRunnable> {

	private Long time;
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

	@Override
	public int compareTo(TimeRunnable other) {
		// TODO Auto-generated method stub
		return this.time.compareTo(other.time);
	}

}