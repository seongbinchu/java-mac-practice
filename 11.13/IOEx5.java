package kr.ac.green;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class IOEx5 {
	public static void main(String[] args) {
		
		InputStream is =null;
		InputStreamReader isr = null;
		
		try {
			is = System.in;
			isr = new InputStreamReader(is);
//			system.in => byte[is]입력받음 =>char[isr]
//			키보드 입력 => is[read] => isr[read] (is가 입력받은 byte)
//			Stream like lego
//			각 특징을 가진 Stream Class 조합 데코레이터패턴
//			조합하는 부분? 어댑터 => 생성자로 결합 나타냄
//			filter 혼자 존재x 무엇을 가공? => 생성자봐야함
//			파일컨트롤[4가지로만 가능]
//				-FileInputStream
//				-FileOutnputStream
//				-FileReader
//				-FileWriter
//			데코레이션패턴 만들기 힘들다 => 데이터베이스
			int data = -1;
			while((data = isr.read())!=-1) {
				System.out.println("입력한 내용 : "+(char)data);
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			IOUtil.closeAll(isr,is);
		}
	}	
}
