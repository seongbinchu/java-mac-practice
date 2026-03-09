package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;

public class IOEx7 {
	public static void main(String[] args) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileWriter fw = null;
		PrintWriter pw = null;
		
		try {
			is = System.in;
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);		
			//줄단위로 읽어옮
			//Buffered 파레트에 임시 저장된 느낌 line별로 읽어 들일 수 있는 이유
			// 하나씩 읽어온 데이터를 엔터 => 라인끝임을 인식
			
			fw = new FileWriter("console2.txt");
			pw = new PrintWriter(fw);
			
			String line = null;
			while( (line = br.readLine()) != null) {
				pw.println(line);
			}
			pw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			IOUtil.closeAll(br,isr,is,fw,pw);
		}
		
	

		
		
		
	}
}
