import java.io.*;


class IOEx6{
	public static void printFileInfo(File f){						// parameter 절대경로 ? 상대경로? => absoluteFile Path
		System.out.println("getName : " + f.getName());
		System.out.println("getParent : " + f.getParent());
		System.out.println("getPath : " + f.getPath());
	}
	public static void main(String[] args){
		File f1 = new File("c:\\Windows\\notepad.exe");

		//System.out.println(f1.canExecute()); //접근?실행?가능한지
		//System.out.println(f1.canRead());	//읽을수있는지
		//System.out.println(f1.canWrite());	//수정가능한지
		
		//printFileInfo(f1);


		//경로 = > 현재위치
		File f2 = new File("some.txt");
		printFileInfo(f2);


		File f3 = f2.getAbsoluteFile();		//상대경로로 만들어진 파일 절대경로 기반으로 변경
		printFileInfo(f3);

		String absolutePath = f2.getAbsolutePath();
		System.out.println(absolutePath);

		System.out.println("=================");

		File f4 = new File("..\\..\\other.txt");
		printFileInfo(f4.getAbsoluteFile());	

		try{
			printFileInfo(f4.getCanonicalFile());
			String cononicalPath = f4.getCanonicalPath();
			System.out.println("path : "+cononicalPath);
		}catch(IOException e){
			e.printStackTrace();
			//file위치 알려줘야하는데(계산된 절대경로가 필요한 경우(ex. 사용자에게 노출되는 경로),이산경로) 절대?상대?
		}
	}
}

/*
	경로
		-절대경로(어디서 접근하든 변하지않는다)
			: c:\first\a.java [root부터 시작]
		-상대경로(접근위치[...]에 따라 변함)
			-같은위치에서 작업하거나 하위위치에 파일 존재할 때
				=> 짧음					=>위치변해도 따라갈 수 있음

			: [first]		a.java or .\a.java [현재위치부터 시작]
			: [second]		..\first\a.java 
							.. = > 상위(c)로 이동
		
		c
			-first
				-a.java
			-second
				-b.java
*/