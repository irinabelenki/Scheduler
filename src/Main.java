public class Main {
	public static void main(String[] args) {
		Scheduler scheduler = new Scheduler();
		long current = System.currentTimeMillis();
		scheduler.schedule(current + 4000, new Runnable() {
			@Override
			public void run() {
				System.out.println("Run1");
			}
		});

		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		scheduler.schedule(current + 3000, new Runnable() {

			@Override
			public void run() {
				System.out.println("Run2");
				
//				scheduler.schedule(current + 2000, new Runnable() {
//					
//					@Override
//					public void run() {
//						System.out.println("Run2-1");
//					}
//				});
			}
		});
		
		try {
			Thread.sleep(200);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		scheduler.schedule(current + 2000, new Runnable() {

			@Override
			public void run() {
				System.out.println("Run3");
			}
		});
	}

}
