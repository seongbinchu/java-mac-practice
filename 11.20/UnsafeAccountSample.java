package kr.ac.green;

class UnsafeAccount {
	private int balance;
	public int getBalance() {
		return balance;
	}
	public void deposit(int val) {
		balance+=val;
	}
	public void withdraw(int val) {
		//임계 구역critical section [공유자원 여러쓰레드가 존재할 수 있는]
//		MUTEX [상호배제]
//		MUTual EXclusion
		if(balance>=val) {
			try {
				Thread.sleep(500);
			}catch(InterruptedException e) {}
			balance -=val;
		}
		
		System.out.println(
				"name :"+ Thread.currentThread().getName()+
				", balance = " + this.getBalance()
		);
	}
}

public class UnsafeAccountSample{
	public static void main(String[] agrs){
		final UnsafeAccount account = new UnsafeAccount();
//		어나니머스클래스 지역변수 변경 불가 부르는것만가능 1.8~
//		final은 모든버전 부르는것 가능
		
		account.deposit(5000);
		Runnable withdrawRun = new Runnable(){
			public void run(){
				for(int i=0;i<10;i++){
					account.withdraw(500);
				}
			}
		};
		Thread t1 = new Thread(withdrawRun);
		Thread t2 = new Thread(withdrawRun);
		//동일한 account[공유자원]에서 출금
		//정보불일치 동기화가 깨졌다
		t1.start();
		t2.start();
	}	
}
