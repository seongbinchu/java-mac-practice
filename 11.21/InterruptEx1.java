package kr.ac.green;

public class InterruptEx1 {
	public static void main(String[] args) {
		Thread t1 = new Thread() {
			@Override
			public void run() {
				try {
					System.out.println("sleep");
					// run => not Runnable 
					Thread.sleep(1000*60);
				}catch(InterruptedException e) {
					System.out.println("wake up");
					System.out.println(
							"isInterrupted :" + isInterrupted()	// false => t1.interrupt(); [true] 
																//	=> 예외처리[false] 
					);
				}
				for(int i=0;i<10;i++) {
					System.out.println(i);
				}
			}
		};
		t1.start();
		t1.interrupt();
		//not Runnable => Runnable
	}
}
