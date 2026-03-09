package ac.kr.green;

class SomeThread extends Thread{
	public SomeThread(String name) {
		super(name);
	}
	@Override
	public void run() {
		String name = this.getName();
		for(int i =0; i<10; i++) {
			System.out.println(name + "is working");
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {}
		}
	}
}
public class RunningTest{
	//main thread 는 우선순위가 NORM_PRIORITY(5)이다
	public static void main(String[] args) {
		SomeThread t1 = new SomeThread("A");
		SomeThread t2 = new SomeThread("B");
		SomeThread t3 = new SomeThread("C");
//		자신이 생성한 쓰레드의 우선순위를 따라간다
//		=> main이 5니까 5로설정됨 [1~10] [MIN 1 NORM 5 MAX 10]
		
//		우선순위설정
//		우선순위를 설정해도 우선순위대로 처리되지는 않는다
//		우선순위가 높아도 실행안될 수 있다
//		자원독점같은 문제
		t1.setPriority(Thread.MIN_PRIORITY);
		t2.setPriority(Thread.NORM_PRIORITY);
		t3.setPriority(Thread.MAX_PRIORITY);
		
		System.out.println(t1.getPriority());
		System.out.println(t2.getPriority());
		System.out.println(t3.getPriority());
		
		t1.start();
		t2.start();
		t3.start();

	}
}