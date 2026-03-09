package kr.ac.green;

class MyPrinter{
	public synchronized void printContents() {
		try {
			wait();
			for(char c='a'; c<='z';c++) {
				System.out.print(c);
			}
			System.out.println();
			notifyAll();
		}catch(Exception e) {}
	}
	public synchronized void marker(){
		try {
			System.out.println("start printing");
			notifyAll();
			wait();
			System.out.println("end printing");
		}catch(Exception e) {}
	}
}

class CallPrint extends Thread{
	MyPrinter src;
	public CallPrint(MyPrinter src) {
		this.src = src;
	}
	public void run() {
		while(true) {
			src.printContents();
		}
	}
}
class CallMaker extends Thread{
	MyPrinter src;
	public CallMaker(MyPrinter src) {
		this.src=src;
	}
	public void run() {
		while(true) {
			src.marker();
		}
	}
}
public class ThreadTest {
	public static void main(String[] args) {
		MyPrinter src = new MyPrinter();
		CallPrint print = new CallPrint(src);
		CallMaker marker = new CallMaker(src);
		
		print.start();
		marker.start();
	}
}
