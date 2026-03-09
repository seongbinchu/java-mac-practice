package kr.ac.green;
/*
 		t.interrupt() 쓰레드 t에게 인터럽트를 건다
 		t.isInterrupt() : 쓰레드 t가 인터럽트를 받았는지 확인한다.
 		Thread.interrupted() : 현재쓰레드(static)가 인터럽트받았는지 확인하고 상태를 초기화한다.
 */
public class InterruptTest2 {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				try {
					System.out.println(
							"try - isInterrupted :" + isInterrupted());
					//인터럽트 받은 기록이 초기화됨(false)
					
					System.out.println(
							"try - isInterrupted :" + Thread.interrupted());
														// isInterrupted 같다
					// Thread가 isInterrupt 받았었다면 기록이 초기화됨
					System.out.println("ok");
					Thread.sleep(4000);`
				}catch(InterruptedException e){
					System.out.println(
						"catch - isInterrupted :" + isInterrupted());
				}
				System.out.println("end of run");
			}
		};
		t.start();
		t.interrupt();
		System.out.println("isInterrupted : "+ t.isInterrupted());
		try {
			Thread.sleep(1000);
		}catch(Exception e) {
			System.out.println("isInterrupted :"+ t.isInterrupted());
		}
	}
}
