package ac.kr.green;

import javax.swing.JOptionPane;

class ThreadEx extends Thread{
	public void run() {
		for(int i=10; i>0; i--) {
			System.out.println(i);
			try {
				sleep(1000);
				// interrupted exception 주의
			}catch(Exception e) {}
		}
	}
}
public class UsingThread {
	public static void main(String[] args) throws Exception{
		ThreadEx th1 = new ThreadEx();
		th1.start();
		
		String input = JOptionPane.showInputDialog("아무 값이나 입력하세요");
		System.out.println("입력하신 값은"+ input+"입니다");
	}
}
////쓰레드를 쓰는이유
////두 프로세스를 동시에 하는것처럼 보이게 할 수 있다
//Thread.State
//-New
//Runnable
//block
//wationg
//Timed_wating