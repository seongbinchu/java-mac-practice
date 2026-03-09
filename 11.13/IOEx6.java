package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;

public class IOEx6 {
	public static void main(String[] args) {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;
		
		FileOutputStream fos =null;
		OutputStreamWriter osw = null;
		PrintWriter pw = null;				// 다양한쓰기연산 ex. print printf println ...
		
		try {
			is = System.in;
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);		
			//줄단위로 읽어옮
			//Buffered 파레트에 임시 저장된 느낌 line별로 읽어 들일 수 있는 이유
			// 하나씩 읽어온 데이터를 엔터 => 라인끝임을 인식
			
			fos = new FileOutputStream("console.txt");
			osw = new OutputStreamWriter(fos);
			pw = new PrintWriter(osw);
			
			String line = null;
			while( (line = br.readLine()) != null) {
				pw.print(line);
			}
			pw.flush();
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			IOUtil.closeAll(br,isr,is,pw,osw,fos);
		}
		
		
		
		
		
		
		
		
		
		
		
		
		
	}
}
