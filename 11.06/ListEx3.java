import java.util.*;
class ListEx3{
	public static void main(String[] args){
		Vector<String> vec = new Vector<>();	// = new Vector<String>(); 생성시 제네릭 타입 생략가능

		vec.add("a");

		System.out.println("size : "+vec.size());
		System.out.println("capacity : "+ vec.capacity());	//사이즈가 넘어갈 때마다 길이가 2배짜리 배열을 생성하여 가변배열 처럼 보이게함
	}
}
