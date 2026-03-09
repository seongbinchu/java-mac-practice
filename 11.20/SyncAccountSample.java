package kr.ac.green;

class SyncAccount{
	private int balance;
	
	public synchronized int getBalance() {
		return balance;
	}
	public synchronized void deposit(int val) {
		balance += val;
	}
	public synchronized void withdraw(int val) {
		//자바가 제공해주는 MUTEX
		//멀티쓰레드를 바탕으로 만들어진 언어라 제공해줌
//		자바의 모든객체 LOCK 정보 가지고 태어남
//		t1이 실행돼 withdraw 접근하면 withdraw LOCK 취득
//		synchronized 메소드 LOCK 취득해야 접근가능
//		문맥교환시도 LOCK 그대로 가져감
//		t2 접근 불가 = >실행불가로 들어감
//		synchronized 메소드 일 끝나면 LOCK반납 후 접근해제
//		락 대신 모니터라는 말도 사용함
//		Thread safe => synchronized 지원하는가 안하는가
//		why 싱글쓰레드차원에서는 필요없음 => 비용추가 하면 안됨
//		Vector 안의 원소의 값변경 지원 x
//		Vector의 기능 은 변경 가능함 get ,set ,add ..
		if (balance >= val) {
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {
			}
			balance -=val;
		}
		System.out.println(
				"name : " +Thread.currentThread().getName() +
				", balance = " + this.getBalance()
		);
	}
}
public class SyncAccountSample {
	public static void main(String[] args) {
		final SyncAccount account = new SyncAccount();
		account.deposit(5000);
		
		Runnable withdrawRun = new Runnable() {
			public void run() {
				for(int i=0; i<10; i++) {
					account.withdraw(500);
				}
			}
		};
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		
		t1.start();
		t2.start();
	}
}
