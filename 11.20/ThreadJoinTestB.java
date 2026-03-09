package kr.ac.green;

public class ThreadJoinTestB {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					System.out.println("MyThread 종료");
					Thread.sleep(3000);
				}catch(Exception e) {}
			}
		};
		t.start();
		try {
			t.join();	
			// main 쓰레드 t의 종료를 기다린다.
			// 소유자 말고 실행한 쓰레드가 영향을 받는다
			// t가 아닌 mainThread
			// mainThread가 실행불가상태가 될 때까지 기다린다
		}catch(InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("main 종료");
	}
}
// main이 뒤로 가게하는 방법1
