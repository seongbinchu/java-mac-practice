package kr.ac.green;

class KeyA{}
class KeyB{}

class Human2{
	private String name;
	private int age;
	private KeyA keyA = new KeyA();
	private KeyB keyB = new KeyB();
	
	public void setName(String name) {
		synchronized(keyA) {
			try {
				this.name = name;
				System.out.println("name changed");
				Thread.sleep(3000);
			}catch(Exception e) {}
		}
	}
	
	public void setAge(int age) {
		synchronized(keyB) {
			try {
				this.age=age;
				System.out.println("age changed");
				Thread.sleep(3000);
			}catch(Exception e) {}
		}
	}
}

public class SynchronizeBlockCase {
	public static void main(String[] args) {
		final Human2 h = new Human2();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				h.setName("°ûÃ¶");
			}
		};
		t1.setName("a");
		
		Thread t2 = new Thread() {
			@Override
			public void run() {
				h.setAge(10);
			}
		};
		t2.setName("vd");
		
		t1.start();
		t2.start();
		
	}
}

//SynchronizemethodCase ÇØ°á¹ý