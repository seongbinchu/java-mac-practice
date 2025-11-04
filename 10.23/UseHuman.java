class Animal{
	private int age;
	
	public Animal(int age){
		this.age = age;
		System.out.println("Animal");
	}
	public void eat(){
		System.out.println("오른손");
	}
	public int getAge(){
		return age;
	}
}
/*
상속받은 클래스 객체 생성 시 부모클래스가 먼저 생성됨
		=> 부모가 생성자가 여러개일시 어떤 생성자를 통해 객체를 만들지 지정을 안해줬음
		public Human(){} => 기본생성자로 생성 =>부모클래스에 기본생성자없으면 에러=> 생성자 어떤거 쓸지 명시적으로 표현해줘야함
		
		this/자신의생성자/자신의 객체주소 
		super/부모의생성자/부모의 객체주소
*/
class Human extends Animal{
	public Human(){
		super(100);					// 인트받는 생성자 사용하겠다. 처음에 와야함 대체x
		System.out.println("Human");
	}
	public Human(int age){			//위 생성자랑 다른게 뭐임
		super(age);
	}
	public void eat(){
		System.out.println("왼손");
		super.eat();				//super => 부모의 객체주소 이것말곤 부모로 접근불가
	}
}
class UseHuman{
	public static void main(String[] args){
		Human h1 = new Human(30);
		h1.eat();
	}
}
