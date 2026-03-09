package kr.ac.green;

class Some{
	public synchronized void todo1(){
		try {
			System.out.println("insdie todo1");
			Thread.sleep(5000);
			System.out.println("t2 state :" + ThreadEx.t2.getState());
			System.out.println("done : todo1");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public synchronized void todo2(){
		try {
			System.out.println("insdie todo2");
			Thread.sleep(5000);
			System.out.println("t1 state :" + ThreadEx.t1.getState());
			System.out.println("done : todo2");
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}


public class ThreadEx {
	public static Thread t1;
	public static Thread t2;
	public static void main(String[] args) {
		final Some s = new Some();
		t1 = new Thread() {
			@Override
			public void run() {
				s.todo1();
			}
		};
		t2 = new Thread() {
			@Override
			public void run() {
				s.todo2();
			}
		};
		t1.start();
		t2.start();
	}
}

// BLOCKED TERMINATED 차이
//todo1 끝나고 락 반납시 t2 대기열복귀
