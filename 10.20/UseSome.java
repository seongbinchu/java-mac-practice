class Some{

	private int num = 1;
	private String letter = "hi";
	// 객체의 정보를 문자열로 변환한다. 생성자가 private멤버 변수 중 사용자에게 보일 것을 지정해서 toString에 문자열로 표현하는것
	// 객체의 모든 멤버 변수 int,String,...등이 모두 문자열로 바뀌는것 아님
	// ?? int
	// 헤더를 변경할 수 없다.
	public String toString(){
		String info = "return " + letter;
		return info;
	}
	public void print(){
		System.out.println(toString());
		System.out.println(this);
	}

	//this = > 자신 객체의 주소값 = s 
 
}

class UseSome{
	public static void main(String[] args){
		Some s = new Some();

		System.out.println(s);
		System.out.println(s.toString()); //같음
		String str = "String : "+s;
		System.out.println(str);

		s.print();
	}
}