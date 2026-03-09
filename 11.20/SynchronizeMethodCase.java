package kr.ac.green;

class Human{
	private String name;
	private int age;
	public synchronized void setName(String name) {
		try {
			this.name = name;
			System.out.println("name changed");
			Thread.sleep(3000);
		}catch(Exception e) {}
	}
	public synchronized void setAge(int age) {
		try {
			this.age = age;
			System.out.println("age changed");
			Thread.sleep(3000);
		}catch(Exception e) {}
	}
}
public class SynchronizeMethodCase {
	public static void main(String[] args) {
		final Human h = new Human();
		Thread t1 = new Thread() {
			@Override
			public void run() {
				h.setName("Ãá½Ä");
			}
		};
		t1.setName("first");
	
		Thread t2 = new Thread() {
			@Override
			public void run() {
			h.setAge(10);
			}
		};
		t1.setName("second");	
		
		t1.start();
		t2.start();
	}
}
