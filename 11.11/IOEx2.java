import java.io.*;
class IOEx2{
	public static void main(String[] args){
		File f = new File("c:\\java\\some.txt");
		//physical file logical? 확인하는방법
		System.out.println(f.exists());

		// 파일 만들기
		// 부모가 존재해야한다

		File parent = new File("c:\\java");
		parent.mkdir();
		// physical file x => exception x

		if(!f.exist()){
			try{
				f.createNewFile(); 
				System.out.println("파일 만들기 성공");
			} catch(IOException e ){
				e.printStackTrace();	//지정된 경로를 찾을 수 없다 부모인 java 존재 x
				System.out.println("파일 만들기 실패");
			}
		}else{
			f.delete();
		}
	}
}