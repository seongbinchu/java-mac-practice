package kr.ac.green;

class Producer extends Thread{
	private MyBox box;
	public Producer(MyBox box) {
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

class Consumer extends Thread{
	private MyBox box;
	public Consumer(MyBox c) {
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

class MyBox{
	private int contents;
	private boolean isEmpty = true;
	public synchronized void get() {
		if(isEmpty) {
			isEmpty = !isEmpty;
			System.out.println(
					Thread.currentThread().getName()+
					" : 소비 " + contents
			);
		}
	}
	public synchronized void put(int value) {
		if(isEmpty) {
			contents = value;
			System.out.println(
					Thread.currentThread().getName() +
					": 생산 "+ value
			);
			isEmpty = !isEmpty;
		}
	}
}
public class ProducerConsumer {
	public static void main(String[] args) {
		MyBox c = new MyBox();
		Producer p1 = new Producer(c);
		Consumer c1 = new Consumer(c);
		Consumer c2 = new Consumer(c);
		
		p1.start();
		c1.start();
		c2.start();
	}
}

//synchronized해도 쓰레드가 언제 어떤 일을 해야할지 강제할 수 없다