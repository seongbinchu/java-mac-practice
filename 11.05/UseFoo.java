import java.util.Scanner;

// generics // T에서 밑에서 생성할 때 String 값으로 주면 모든 T String타입 변경
// T 는 실행 중 정의되기 때문에 제네릭들어있는 클래스 외부에서 사용 불가능
class Foo<T>{
	private T member;

	public Foo(T member){
		setMember(member);
	}

	public T getMember(){
		return member;
	}
	public void setMember(T member){
		this.member = member;
	}
}

interface IBar<T>{
	void todo(T some);
}
// 인터페이스에 사용 시 구현 클래스에 붙여준다.
class BarImpl implements IBar<String>{
	@Override
	public void todo(String Some){
	
	}
}
class UseFoo{
	public static void main(String[] args){
		Foo<String> f1 = new Foo<String>("abc");
		//generic
		Foo<Scanner> f2 = new Foo<Scanner>(new Scanner(System.in));
	}
}
