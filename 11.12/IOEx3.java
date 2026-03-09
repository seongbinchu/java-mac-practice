package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class IOEx3 {
	public static void main(String[] args) {
//		FileInputStream fis = null;
//
//		try {
//			fis = new FileInputStream("data.txt");
//
//			int data = -1;
//			while ((data = fis.read()) != -1) {
//				System.out.println((char) data);
//			}
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} finally {
//			try {
//				fis.close();
//			} catch (Exception e) {}
//		}

		try (FileInputStream fis = new FileInputStream("data.txt")) {
			int data = -1;
			while ((data = fis.read()) != -1) {
				System.out.println((char) data);
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
