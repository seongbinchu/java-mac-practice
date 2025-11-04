abstract class Animal{
	abstract public void sound();
/*
public void sound(){
		문제점1 비워 둔 것은 하위클래스에서 오버라이드 하라는 뜻
		 하지만 하위클래스에서 사용해도 되는지 알 수가 없음
		 문제점2 헤더가 다 다를 수 있음 sound , bark , ...
}
*/

}
class Cat extends Animal{
	@Override
	public void sound(){
		System.out.println("cat");
	}
}
class Dog extends Animal{
	@Override
	public void sound(){
		System.out.println("dog");
	}
}
abstract class Tutle extends Animal{
	//상속받은 추상메소드 추상클래스이기 때문에 오류x
	//추상클래스라서 객체로 사용 x 하위클래스 존재x 의미없음
}
class UseAnimal{
	public static void main(String[] args){
		Cat c1 = new Cat();
		Dog d1 = new Dog();
		c1.sound();
		d1.sound();
	}
}
