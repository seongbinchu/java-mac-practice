package kr.ac.green;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.JOptionPane;

public class Crolling {
	public static void main(String[] args) {
		String urlString;
		do {
			urlString = JOptionPane.showInputDialog("URL");
		}while(urlString==null||urlString.trim().length()== 0);
		
		HttpURLConnection con = null;
		
		try {
			URL url = new URL(urlString);
			con = (HttpURLConnection)url.openConnection();
			String msg = "응답코드 이상 : ";
			int responseCode = con.getResponseCode();
			
			if(responseCode == HttpURLConnection.HTTP_OK
				|| responseCode == HttpURLConnection.HTTP_MOVED_PERM
				|| responseCode == HttpURLConnection.HTTP_MOVED_TEMP
			) {
				try(
					InputStream is = con.getInputStream();
					BufferedReader br = new BufferedReader(
						new InputStreamReader(is,"utf-8")
					);
					FileOutputStream fos = new FileOutputStream("result.html");
					OutputStreamWriter osw = new OutputStreamWriter(fos,"utf-8");
				){
					String line = null;
					StringBuffer buf = new StringBuffer();
					
					while( (line = br.readLine()) != null) {
						buf.append(line + "\n");
					}
					osw.write(buf.toString());
					osw.flush();
					msg="작업완료";
				}
			}else {
				msg += "responseCode";
			}
			JOptionPane.showInternalMessageDialog(null, msg);
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			try {
				con.disconnect();
			}catch(Exception e ) {}
		}
	}
}
