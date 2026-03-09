package ac.kr.green;

public class DaemonThreadTest {
	public static void main(String[] args) {
		Thread t = new Thread() {
			public void run() {
				try {
					System.out.println("MyThread 시작");
					Thread.sleep(5000);
					System.out.println("MyThread 종료");
				}catch(Exception e) {}
			}
		};
		// 반드시 start() 호출 전에 사용해야 한다!
		// 데몬쓰레드 : 일반 쓰레드들이 끝나면 같이 끝남
		// foreground 말고 background작업들에 사용
		//t.setDaemon(true);
		t.start();
		
		System.out.println("main()종료");
	}
}