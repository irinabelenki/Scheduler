import java.util.Comparator;
import java.util.PriorityQueue;

public class Scheduler {

	private Comparator<TimeRunnable> comparator = new TimeRunnableComparator();
	private PriorityQueue<TimeRunnable> queue = new PriorityQueue<TimeRunnable>(
			10, comparator);

	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		long current = System.currentTimeMillis();
		scheduler.schedule(current + 3000, new Runnable() {

			@Override
			public void run() {
				System.out.println("Run1");
			}
		});
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		scheduler.schedule(current + 3000, new Runnable() {

			@Override
			public void run() {
				System.out.println("Run2");
				scheduler.schedule(current + 2000,
						new Runnable() {

							@Override
						public void run() {
								System.out.println("Run22");
							}
						});
			}
		});
	}

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
			if (one.getTime() < two.getTime()) {
				return -1;
			}
			if (one.getTime() > two.getTime()) {
				return 1;
			}
			return 0;
		}
	}

}
