import java.util.PriorityQueue;

public class WorkingThread extends Thread {

	private PriorityQueue<TimeRunnable> queue;

	public WorkingThread(PriorityQueue<TimeRunnable> queue) {
		this.queue = queue;
	}

	public void run() {
		System.out.println("Working thread started");
		while (true) {
			TimeRunnable timeRunnable = (TimeRunnable) queue.peek();

			if (timeRunnable != null) {
				System.out.println("Peek, queue size=" + queue.size());
				long timeBefore = System.currentTimeMillis();
				long timeDiff = timeRunnable.getTime() - timeBefore;
				if (timeDiff > 0) {
					synchronized (queue) {
						try {
							queue.wait(timeDiff);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

						if (System.currentTimeMillis() >= timeBefore + timeDiff) {
							new Thread(timeRunnable.getRunnable()).start();
							queue.remove(timeRunnable);
							System.out.println("Started and removed, queue size=" + queue.size());
						} else {
							System.out.println("Wait interrupted");
						}
					}// synchronized
				} else {
					queue.remove(timeRunnable);
					System.out.println("Removed from queue");
				}
			}
		}// true
	}
}
