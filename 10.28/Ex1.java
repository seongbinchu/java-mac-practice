class Fruit{
}
class Apple extends Fruit implements ISweet,IEatTable{
	@Override
	public void makeSound(){
		System.out.println("apple");
	}
}
class Banana extends Fruit implements ISweet,IEatTable{
	@Override
	public void makeSound(){
		System.out.println("Banana");
	}
}
class Kiwi extends Fruit implements IEatTable{
	@Override
	public void makeSound(){
		System.out.println("kiwi");
	}
}
interface ISweet{
	//구분을 위해 사용되는 인터페이스 빈 인터페이스 : Mark interface
}
interface IEatTable{
		void makeSound();
}
class Meat implements IEatTable{
	@Override
	public void makeSound(){
		System.out.println("Meat");
	}
}
class Boy {
	public void eat(IEatTable food){
	}
}
class Girl {
	//단거만먹음
	public void eat(ISweet some){
	}
}
class Ex1{
	public static void main(String[] args){
		Boy boy = new Boy();
		Apple a = new Apple();
		Banana b = new Banana();
		Kiwi k = new Kiwi();
		Meat m = new Meat();
		boy.eat(a);
		boy.eat(b);
		boy.eat(k);
		boy.eat(m);
		m.makeSound();

		Girl girl = new Girl();
		girl.eat(a);
		girl.eat(b);
	}
}
