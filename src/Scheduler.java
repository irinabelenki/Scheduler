public class Scheduler {

	private BlockingQueue<Runnable> queue = new BlockingQueue<Runnable>(10);
	
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		scheduler.schedule(new Runnable() {

			@Override
			public void run() {
				System.out.println("Run1");
			}
		});
	}

	Scheduler() {
		new WorkingThread(queue).start();
	}

	public void schedule(Runnable runnable) {
		try {
			queue.put(runnable);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
