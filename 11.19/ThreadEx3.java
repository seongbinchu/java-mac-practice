package ac.kr.green;

class ThreadEx3 extends Thread{
	@Override
	public void run() {
		for(int i=0;i<30000;i++) {
			System.out.print("|");
		}
		System.out.println(
		"소요시간2:"+(System.currentTimeMillis()-UsingThreadProcess.startTime)
				
		);
	}
}
//context - switching 문맥교환
// a = b 1.b값 읽음2.b값복사3.a에대입
//문장별로 문맥교환 x 1하고 끝날 수 도 있다
class UsingThreadProcess{
	static long startTime =0;
	
	public static void main(String[] args) {
		ThreadEx th1 = new ThreadEx();
		
		startTime = System.currentTimeMillis();
		th1.start();
		for(int i=0;i<30000;i++) {
			System.out.print("-");
		}
		System.out.println(
				"소요시간1:"+
		(System.currentTimeMillis()- UsingThreadProcess.startTime)
		);
	}
}