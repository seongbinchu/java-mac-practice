package kr.ac.green;

public class InterruptEx2 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				long count = 0;
				while(!isInterrupted()) {
					count++;
				}
				System.out.println("interrupted -> count "+ count);
				System.out.println("isInterrupted :" + isInterrupted());
			}
		};
		t1.start();
		try {
			Thread.sleep(1000);
		}catch(Exception e) {}
		t1.interrupt();
	}
}
