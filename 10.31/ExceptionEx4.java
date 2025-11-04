import java.io.FileReader;
class ExceptionEx4{

	public static void makeException(){
		FileReader fr = new FileReader("a.txt");
	}

	public static void main(String[] args){
		
	}
}
/*
예외처리 안하면 에러 RuntimeException 예외처리 안해도 컴파일가능
*/