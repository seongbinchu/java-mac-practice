package kr.ac.green;

public class Ex1 {
	public static void main(String[] args) {
		Thread t = new Thread() {
			@Override
			public void run() {
				int i=0;
				while(i<10000) {
					i++;
				}
				System.out.println("current state1 :"+isInterrupted());
				try {
					Thread.sleep(10000);
				}catch(InterruptedException e) {
					System.out.println("current state2 :"+isInterrupted());
				}
			}
		};
		t.start();
		t.interrupt();
		System.out.println("end of main");
	}
}
