package ac.kr.green;

public class RunnableThreadTest {
	public static void main(String[] args) {
		// Thread 생성자에 RunnableThread 인스턴스를 파라미터로 전달.
		RunnableThread r = new RunnableThread();
		//r는 쓰레드아님. 쓰레드가 할일을 나타내는 객체 [Runnable의 경우]
		// t 가 쓰레드
		Thread t = new Thread(r);
		t.start();
		System.out.println("end of main");
	}
}
