package kr.ac.green;

import java.io.Closeable;

public class IOUtil {
	
	public static void closeAll(Closeable... some) {
		for(Closeable temp : some) 
			try {
				temp.close();
			}catch(Exception e) {}
	}
}
