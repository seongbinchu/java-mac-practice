 /*
  프로세스 = > 실행중인 프로그램
  
  멀티태스킹
  
  병렬parallel
  -동시에 처리
  특정 시간대에 여러 일을 함
  다수코어부터 가능
  
  병행Concurrent
  시분할방식 특정 시간대에 하나의 일만 함
  싱글코어로도 가능
  
  스케쥴링
  os가 프로세스 명령
  어떤 시점에 어떤 프로세스가 실행될지 예측하기 어려움
  ex.자바프로그램 os  = > JVM  
  
  thread
  프로세스 내 일 단위
   -프로그램 쓰레드 집합 =>프로그램의 종료는 모든 쓰레드의 종료
   //thread call-stack 모두 비워지면 프로그램 종료 ?
   Some.todo(); 누가시키는가? thread
   메인메소드 => main thread
 */
package ac.kr.green;

public class ExtendThread extends Thread {
	//run()[해당 thread가 할 일]를 오버라이딩하여 재정의
	public void run() {
		System.out.println("Thread 클래스를 상속");
	}
}
