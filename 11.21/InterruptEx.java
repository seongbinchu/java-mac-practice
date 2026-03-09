package kr.ac.green;

class Dummy{
	public synchronized void todo() {
		try {
			System.out.println("t1(run) :"+ Thread.currentThread().isInterrupted());
			System.out.println("start....");
			wait();
		}catch(InterruptedException e) {
			System.out.println("interrupted!!");
			System.out.println("t1(run) :"+ Thread.currentThread().isInterrupted());
		}
	}
}
public class InterruptEx {
	public static void main(String[] args) {
		final Dummy d = new Dummy();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				d.todo();
				System.out.println("i'm daed");
			}
		};
		t1.start();
		try {
			Thread.sleep(3000);
		}catch(Exception e) {}
		t1.interrupt();
		System.out.println("t1(main) :"+ Thread.currentThread().isInterrupted());
		// 실행 불가능한 쓰레드 다른 쓰레드가 interrupt해주면 예외를 발생시킴
		// 결과적으로 깨어나게됨
		// 실행 중에 받으면 아무일 x
		// interrupt 미리 받았으면 실행불가상태 전이되자마자 예외발생 
	}
}
