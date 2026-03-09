package ac.kr.green;

public class CreateThread {
	public static void main(String[] args) {
		ThreadEx1_1 t1 = new ThreadEx1_1();
	//	[runnable 의 경우 이는 할일이고 쓰레드아니지만 ex1_1 쓰레드 상속받았기 때문에 이는 쓰레드]
		
		Runnable r = new ThreadEx1_2();
		Thread t2 = new Thread(r);
		
		t1.start();
		t2.start();
	}
}
