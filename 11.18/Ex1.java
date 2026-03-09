package kr.ac.green;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/* Obejcte... Data...putStream.. 을 상속받음 => 눈으로 확인할 수 없음
 * ObjectInputStream
 * ObjectOutputStream
 * 
 * Object ==write> File ===read> Object
 * 파일저장시점 =>> 세이브파일 ==> 로드파일
 * byte단위로 쪼갬 [마샬링(쓰기)] ==> 객체로 원복[언마샬링(읽기)]
 * 마샬링 ,언마샬링 한 바이트단위 데이터가 스트림을 통해 흐르는 것 직렬화(serialization),객체직렬화
 * 직렬화 가능한 객체 serializable 구현해야함 Serializable[구분하기위한 인터페이스 마크인터페이스]
 * */
public class Ex1 {
	
	public static void write() {
		Other other = new Other(3.14);
		Some s = new Some(100,"abc",other);
		
		FileOutputStream fos =null;
		ObjectOutputStream oos = null;
		
		try {
			fos = new FileOutputStream("some.dat");
			oos = new ObjectOutputStream(fos);
			// 파일 1개 당 객체 1개 쓸 수 있음 <필수> 이론적으로는 여러개 가능하나 객체간 경계모호해짐
			
			/*Vector<Some> vec = new Vector<Some>();
			vec.add(new Some(100,"abc"));
			vec.add(new Some(100,"abc"));
			vec.add(new Some(100,"abc"));				원소들도 모두 직렬화가능해야함
			vec.add(new Some(100,"abc"));				원소가 가진 원소들도 직렬화 가능해야함
			vec.add(new Some(100,"abc"), new other(1.2));	
			굳이 여러개 쓰고 싶으면 다수데이터 사용
			*/	
			oos.writeObject(s);
			oos.flush();	//두가지는 
			oos.reset();	//메모리 leck 방지위해 필수
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}finally {
			IOUtil.closeAll(oos,fos);
		}
	}
	/*
	 * InvailidClassException
	 * The serializable class Some does not declare a static final serialVersionUID field of type long
	 * write 할 때 정해주지 않으면 컴파일 시 임의의 UID값이 부여돼 file에 존재하게 됨
	 * read 할 때 동일한 UID값을 지닌 클래스파일이 없으면 해당 예외 발생함
	 * 순서를 바꾸거나 공백을 지우거나 하면 컴파일 다시함 => UID변경될 가능성 있음 => read안됨
	 * UID 값 임의로 정해주지 않으면 read 실패할 가능성 존재하게됨
	 * => serialVersionUID 명시적으로 지정해주는것이 좋음
	 *	private static final long serialVersionUID = 1L; 
	 * */
	public static void read() {
		
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		
		try {
			fis = new FileInputStream("some.dat");
			ois = new ObjectInputStream(fis);
			Object o = ois.readObject();
			//Object로 존재 형변환필요
			System.out.println(o);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e) {
			e.printStackTrace();
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
			//객체 ==> 파일 ==> 객체
			//        파일에 클래스구조(멤버변수) 같은 것이 들어가지만
			//		메소드의 경우 some.class 클래스파일에서 데이터를 가져온다
			//		직렬화하고 원복시 클래스파일이 반드시 필요함
			//		해당오류는 클래스파일이 없을 때 발생
		}finally{
			IOUtil.closeAll(ois,fis);
		}
		
	}
	public static void main(String[] args) {
		write();
		read();
	}
}
