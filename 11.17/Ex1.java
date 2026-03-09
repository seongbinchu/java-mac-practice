package kr.ac.green;

// public, 생략
class Outer {
	
	private Inner some;
	private int num = 4;
	
	public Outer() {
		some = new Inner();
	}
	
	public void printUsingInner() {
		some.printNum();
	}
	
	// 4가지 모두 사용 가능
	// Outer 만을 위한 Inner가 된다.
	// Outer 객체 생성 후, Inner 객체 생성 가능
	public class Inner { 
		public void printNum() {
			System.out.println(num);
		}
	}
	
	public static class Other {
		public void printNum() {
			// static 요소만 접근 가능하다.
			// System.out.println(num);
		}
	}
}

public class Ex1 {
	public static void main(String[] args) {
//		Outer o = new Outer();
//		Outer.Inner inner = o.new Inner(); // 반강제로 만든 거고 이런 식으로 잘 안씀
		
		Outer.Other o = new Outer.Other(); // Other 클래스에 static이 붙어있어서 Outer 객체를 
											// 만들지 않아도 Other 객체를 만들수있다.
	}
}