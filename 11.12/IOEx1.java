package kr.ac.green;

import java.io.*;
/*

Stream
	-연속성
	-방향성(단반향)
		-읽기와 쓰기 방향다름
	-순차적임
InputStream
	-read();
		오버라이딩 메소드 리턴 인트 => 실제값은 바이트 형변환 귀찮아서 인트리턴
	-close();
		자원해제 리턴 인트 => 스트림 끝나면 -1리턴
OutputStream
	-write(int b)
		파마리터 바이트지만 형변환 귀찮아서 인트로 받는다
	-flush()
		버퍼에 들어가있는 출력흐름 강제로 비워낸다

Read
	-<<abstract>>read(char[],int off,int leg) = > InputStream read 받아 변경사용
	-read() = > byte => single char	
		읽기는 무엇을 어떻게 읽어야할 지 모름


Write
	- write(char[] cbuf,int off,int len) <<abstract>>
		쓰기는 무엇을 써야할 지 알고있다
		write 여러 메소드를 효율적으로 abstarct write에서 사용
	- write(int c)
*/

class IOEx1 {
	public static void main(String[] args) {
		FileOutputStream fos = null;
		try {
			// fos = new FileOutputStream("data.txt");//현재위치 exists();
			File f = new File("data.txt");
			fos = new FileOutputStream(f, true);
			// 해당 파일에 덭붙여서 write하기
			// write의 기본은 over-write 덮어쓰기 기존 데이터 손실가능성 의미
			// byte연산에 char = > 훼손
			// 연산 끝나면 자원해제
			// 쓰기작업 ->physicalFile 자동으로 생성
			// 생성자로 덮어쓰기,이어쓰기 선택 가능
			fos.write('a');
			fos.write('b');
			fos.write('c');

		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (fos != null) {
				try {
					fos.close(); // 닫기위해 위에서 null초기화
				} catch (Exception e) {
				}
			}
		}
	}
}
/*
 * try-with-resorce try에 담으면(AutoCloseable) 자동으로 자원해제 자원해제 시 같이 해야할 기능 있을 때
 * finally써야함 finllay = > AutoCloseable 먼저해제? finally먼저해제? 순서? File f = new
 * File("data.txt"); try(FileOutputStream fos = new FileOutputStream(f,true)){
 * fos.write)('a'); }catch(IOException e){ e.printStackTrace(); }
 */