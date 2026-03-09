import java.io.*;
class IOEx3{
	public static void main(String[] args){
		c:\\java\\a\\b\\c\\d\\e\\f\\other.txt

		File dir = new File("c:\\java\\a\\b\\c\\d\\e");
		dir.mkdirs();

		File file = new File(dir,"other.txt");

		try{
			file.createNewFile();
		}catch(IOException e){
			e.printStackTrace();
		}
		file.delete();
		dir.delete();
		directory 지울 때 내부에 아무것도 없어야 가능
	}
}
