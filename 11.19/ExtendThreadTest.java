package ac.kr.green;

public class ExtendThreadTest {
	public static void main(String[] args) {
		ExtendThread et = new ExtendThread();
		//[객체를 만들 때 까지 흐름은 한개]
		// start() 를 이용하여 스레드를 시작 시킨다.
		// 이후 ExtendThread 의 run()가 실행되고 run()가 종료되면 바로
		// ExtendThread 가 소멸된다.
		et.start();
		//[쓰레드 2개 추가된 쓰레드 => run 스케줄링 경쟁의 대상]
		//[흐름 2개,스케줄링 대상,쓰레드 대기열로 들어감[일한다는의미x JVM 스케줄링 포함되면일함]]
		System.out.println("end of main");
		
	}
}
