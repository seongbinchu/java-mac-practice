package kr.ac.green;

import java.io.FileWriter;
import java.io.IOException;

public class IOEx2 {
	public static void main(String[] args) {
		FileWriter fw = null;
		
		try {
			fw = new FileWriter("some.txt");
			fw.write("이거슨 String 입니다.");
			fw.write("잘 나오나 ?");
			fw.flush();									//버퍼 다 차지 않아도 비우기
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				fw.close();
			}catch(Exception e) {}
		}
	}
}
