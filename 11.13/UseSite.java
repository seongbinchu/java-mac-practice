package kr.ac.green;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Hashtable;
import java.util.Map;

class User{
	private String id;
	private String password;
	private String name;
	private int age;
	
	public User(String id, String password, String name, int age) {
		setId(id);
		setPassword(password);
		setName(name);
		setAge(age);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}
	
}

class Site{
	private Map<String, File> Users = new Hashtable<>();

	public void signUp() {
		InputStream is = null;
		InputStreamReader isr = null;
		BufferedReader br = null;

		PrintWriter pw = null;
		try{
			is = System.in;
			isr = new InputStreamReader(is);
			br = new BufferedReader(isr);

			String firstLine= null;
			String line = null;
			System.out.println("id : ");
			
			pw = new PrintWriter(firstLine+".txt");
			
			
			while( (line = br.readLine()) != null) {
				pw.print(line);
			}
			pw.flush();
			
		}catch(IOException e) {
			e.printStackTrace();
		}finally {
			IOUtil.closeAll(br,isr,is,pw);
		}
	} 
	
}
public class UseSite {
	public static void main(String[] args) {
		Site s = new Site();
		s.signUp();
	}
}





//회원가입,로그인이 가능한 클래스 구현
//
//회원은 id,password , 이름 , 나이정보로 구성
//회원의 정보는 파일로 저장되고 이를 기반으로 로그인이 가능해진다
//복수회원이 가입할 수 있다
//
//user
//-id					
//-password			
//-name
//-age
//
//
//
//site
//-유저파일 맵 = > key id line 별로 받아와서 첫번째 줄 password equlas 로그인?
//
//-로그인
//-회원가입 = > id , password name age 일력받음 파일만들 때 조건문 아이디 같으면 불