package kr.ac.green;
class Some2{
	private Object key = new Object();
	public void methodA() {
		Thread temp = Thread.currentThread();
		//synchronized(LOCK 대상)
		//synchronized 메소드 LOCK 주체 정할 수 없다 무조건 This 
		// = > 객체 내 다른 synchronized메소드 같이 동기화
		// => 메소드 전체가 동기화
		//synchronized Block LOCK 주체 정할 수 있음 , 어디까지 동기화할건지 조절가능
		//비용은 적게드나 문제 발생률이 높을 수 있다
		// 병목구간을 최소화 할 수 있다 . 구간설정가능
		
		// A , B Thread1[A], Thread2[B] 같이 동기화 되버리면
		// Thread1이 A를 건드릴 때 상관없는 Thread2 실행안됨
		synchronized(this){
			for(int i=0; i<100; i++) {
				try {
					System.out.println(
							temp.getName() + " -> doing methodA"
					);
				}catch(Exception e) {}
			}
		}
	}
	public void methodB() {
		Thread temp = Thread.currentThread();
		synchronized(key){
			for(int i=0; i<100; i++) {
				try {
					System.out.println(
							temp.getName() + " -> doing methodB"
					);
					Thread.sleep(10);
				}catch(Exception e) {}
			}
		}
	}
}
public class ThreadTest {
	public static void main(String[] args) {
		final Some2 s = new Some2();
		
		Thread t1 = new Thread() {
			@Override
			public void run() {
				s.methodA();
			}
		};
		Thread t2 = new Thread() {
			@Override
			public void run() {
				s.methodB();
			}
		};
		t1.start();
		t2.start();
	}
}
