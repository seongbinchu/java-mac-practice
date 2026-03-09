package kr.ac.green;

import java.io.FileReader;
import java.io.IOException;

public class IOEx3 {
	public static void main(String[] args) {
		try(FileReader fr = new FileReader("Some.txt")){
			
			int data = -1;
			
			while((data = fr.read()) != -1) {
				System.out.print((char)data);
				
			}
		}catch(IOException e) {
			e.printStackTrace();
		}
	}

}
