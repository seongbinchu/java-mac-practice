package kr.ac.green;

import java.io.IOException;
import java.io.InputStream;

public class IOEx4 {
	public static void main(String[] args) {
		//표준 입력 장치의 입력을 읽는(byte) 스트림
		InputStream is = System.in;
		
		System.out.println("\\r"+(int)'\r');
		System.out.println("\\n"+(int)'\n');
		int data = -1;
		try {
//			enter : \r\n
//			ctrl + z : 입력끝
			while((data = is.read()) != -1) {
				System.out.println(">>"+data+"<<");
			}
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				is.close();
			}catch(Exception e) {}
		}
	}
}
