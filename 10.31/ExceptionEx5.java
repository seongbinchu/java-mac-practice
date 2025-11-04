import java.io.*;
class ExceptionEx5{
	public static void first(){
		try{
			second();
		}catch(FileNotFoundException e){}
	}

	public static void second() throws FileNotFoundException {
		new FileReader("a.txt");
	}
	public static void main(String[] args){
		first();
	}
}

/*
unreported exception ?
FileNotFoundException 예외처리책임전가
public method try-catch x because public
try-finally o essentical point
*/
