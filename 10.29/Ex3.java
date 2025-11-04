interface FlyNoWay extends FlyBehavior{
	
}
interface FlyWithWing extends FlyBehavior{
	
}
interface FlyBehavior{
	abstract void fly();
}
interface QuackBehavior{
	abstract void quack();
}
interface Quack extends QuackBehavior{
}
interface Squeak extends QuackBehavior{
}
interface MuteQuack extends QuackBehavior{
}
abstract class Duck implements FlyBehavior,QuackBehavior{
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;

	public void performQuack(){
	}
	public void performFly(){
	}
	public void swim(){
	}
	public void display(){
	}
	public void setFlyBehavior(FlyBehavior temp){
		this.FlyBehavior=FlyBehavior temp;
	}
	
	
}

class MallardDuck extends Duck{
	@Override
	public void display(){}
	@Override
	public void fly(){}
	@Override
	public void quack(){}
}
class RedHeadDuck extends Duck{
	@Override
	public void display(){}
	@Override
	public void fly(){}
	@Override
	public void quack(){}
}
class RubberDuck extends Duck{
	@Override
	public void display(){}
	@Override
	public void fly(){}
	@Override
	public void quack(){}
}
class DecoyDuck extends Duck{
	@Override
	public void display(){}
	@Override
	public void fly(){}
	@Override
	public void quack(){}
}

class Ex3{
	public static void main(String args[]){
		RedHeadDuck r = new RedHeadDuck();
		r.performFly();
		r.setFlyBehavior(new FlyNoWay());
		r.performFly();
	}
}