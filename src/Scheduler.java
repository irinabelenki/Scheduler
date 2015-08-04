import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler {

	private Comparator<TimeRunnable> comparator = new TimeRunnableComparator();
	private PriorityQueue<TimeRunnable> queue = new PriorityQueue<TimeRunnable>(
			10, comparator);

	Scheduler() {
		new WorkingThread(queue).start();
	}

	public void schedule(long time, Runnable runnable) {
		TimeRunnable head = (TimeRunnable) queue.peek();
		queue.add(new TimeRunnable(time, runnable));
		System.out.println("Added, queue size=" + queue.size());
		synchronized (queue) {
			try {
				if (head != null && time < head.getTime()) {
					queue.notifyAll();
					System.out.println("Notify");
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public class TimeRunnableComparator implements Comparator<TimeRunnable> {
		@Override
		public int compare(TimeRunnable one, TimeRunnable two) {
			return one.compareTo(two);
		}
	}

}
