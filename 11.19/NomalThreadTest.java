package ac.kr.green;

public class NomalThreadTest {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				try {
					Thread.sleep(5000);
					System.out.println("MyThread 종료");
				}catch(Exception e) {}
			}
		};
		t.start();
		System.out.println("main()종료");
	}
}
