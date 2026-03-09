import java.io.*;
class IOEx4{
	public static void main(String[] args){
		File dir = new File("c:\\java\\temp\\");
		File f = new File("another.txt");

		dir.mkdir();
		dir.delete();

		System.out.println(dir.isFile());
		System.out.println(dir.isDirectory());
		//physicalfile¸¸°¡´É
	}
}
