
public class WorkingThread extends Thread {
	
	private BlockingQueue<Runnable> queue;
	
	public WorkingThread(BlockingQueue<Runnable> queue) {
		this.queue = queue;
	}

	public void run() {
		while (true) {
			try {
				Runnable r = (Runnable)queue.get();
				r.run();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
