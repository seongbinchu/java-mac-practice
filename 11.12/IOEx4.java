package kr.ac.green;

import java.io.*;

public class IOEx4 {

	/*
	 * 파라미터로 전달받은 파일을 복사한다 단 파일명은 some.txt 인경우 _copy를 붙여 구성한다
	 */

	public static void copyFile(File f) {
		
		File file = f.getAbsoluteFile();				//param 절대? 상대? 경로가있어야 파일위치에 파일생성
		String name = file.getName();					// C:\Users\GGG\Desktop\eclipse\11_12\data.txt =>getName() data.txt
		int idx = name.lastIndexOf(".");				// a.b.c.d.e.txt => .찾기 어려움 확장자.last
		String preName = name.substring(0,idx);			
		String ext = name.substring(idx);
		name = preName+"_copy"+ ext;
		String path = file.getParent() + "\\" + name;	//모든부모
		try (
				FileInputStream fis = new FileInputStream(f);
				FileOutputStream fos = new FileOutputStream(name);	//stream 파라미터 => name은 경로라고 생각해야할듯
		) {
			int data = -1;
			while((data = fis.read()) != -1) {
				fos.write(data);
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
			File f = new File("data.txt");		//파일 생성자에 파일이름만 넣으면 상대경로
			copyFile(f);
	}
}
