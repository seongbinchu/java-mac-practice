interface FlyNoWay implements FlyBehavior{

}
interface FlyWithWing implements FlyBehavior{

}
interface FlyBehavior{
	abstract void fly();
}
interface QuackBehavior{
	abstract void quack();
	
}
interface Quack implements QuackBehavior{
	abstract void quack(){};
}
interface Squeak implements QuackBehavior{
	abstract void quack(){};
}
interface MuteQuack implements QuackBehavior{
	abstract void quack(){};
}

abstract class Duck implements flyBehavior,QuackBehavior{
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;

	public void performQuack(){
	}
	public void performFly(){
	}
	public void swim(){
	}
	public abstract void display(){
	}
	public void setFlyBehavior(FlyBehavior f){
		this.FlyBehavior=FlyBehavior;
	}
	public void setQuackBehavior(QuackBehavior q){
		this.setQuackBehavior =QuackBehavior;
	}
}

class MallardDuck extends Duck{
	@Override
	public display(){}
}
class RedHeadDuck extends Duck{
	@Override
	public display(){}
}
class RubberDuck extends Duck{
	@Override
	public display(){}
}
class DecoyDuck extends Duck{
	@Override
	public display(){}
}

class Ex2{
	public static void main(String args[]){
		
	}
}