package kr.ac.green;

class MyBox2{
	private int contents;
	private boolean isEmpty = true;
	public synchronized int get() {
		while(isEmpty) {
			try {
				wait();
			}catch(InterruptedException e) {}
		}
		isEmpty = !isEmpty;
		notifyAll();
		System.out.println(
				Thread.currentThread().getName()+" : 소비 "+ contents
		);
		return contents;
	}
	public synchronized void put(int value) {
		while(!isEmpty) {
			try {
				wait();
			}catch(InterruptedException e) {}
		}
		contents = value;
		System.out.println(
				Thread.currentThread().getName()+ ": 생산 "+value
		);
		isEmpty = !isEmpty;
		notifyAll();
	}
}
class Producer2 extends Thread{
	private MyBox2 box;
	public Producer2(MyBox2 box) {
		this.box=box;
	
	}
	public void run() {
		for(int i=0; i<20;i++){
			box.put(i);
			try {
				sleep(100);
			}catch(InterruptedException e) {}
		}
	}
}

class Consumer2 extends Thread{
	private MyBox2 box;
	public Consumer2(MyBox2 c) {
		box = c;
	}
	public void run() {
		for(int i=0;i<10;i++) {
			box.get();
			try {
				sleep(100);
			}catch(InterruptedException e) {}
		}
	}
}

public class ProducerConsumer2 {
	 public static void main(String[] args) {
		MyBox2 c = new MyBox2();
		Producer2 p1 = new Producer2(c);
		Consumer2 c1 = new Consumer2(c);
		Consumer2 c2 = new Consumer2(c);
		
		p1.start();
		c1.start();
		c2.start();
	}

}
/*
자바는 멀티쓰레드 기반 = > 모든 객체 공유자원될 가능성있음
Object에 wait notifyAll 
		=> 메소드의 소유자가 영향 x 실행한 쓰레드 영향
		=> 호출 시점 정해져있음 => LOCK취득했을 때만 가능[synchronized]
				wait => 자신들고 있던 LOCK을 풀고 실행불가
				notifyAll => wait된 쓰레드들 전부 깨우는 메소드
				notify = > wait된 쓰레드들 무작위로 1개 깨움 [기아 유발 가능]
쓰레드들이 할일이 남았는데 모두 실행불가할 때 [데드락] 쓰레드의 실행상태가 변하지않음
[라이브락] 쓰레드 상태는 변하지만 프로그램 진행이 안되는 것

*/