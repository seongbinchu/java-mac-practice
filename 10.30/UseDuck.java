interface QuackBehavior {
	abstract void quack();
}
class Quack implements QuackBehavior {
	@Override
	public void quack() {
		//꽉꽉
	}
}
class Squeak implements QuackBehavior {
	@Override
	public void quack() {
		// 찍찍
	}
}
class MuteQuack implements QuackBehavior {
	@Override
	public void quack() {
		// NO THING
	}
}

interface FlyBehavior {
	abstract void fly();
}
class FlyWithWing implements FlyBehavior{
	@Override
	public void fly() {
		System.out.println("날개로 난다~");
	}
}
class FlyNoWay implements FlyBehavior {
	@Override
	public void fly() {
		// NO THING
	}
}
abstract class Duck {
	
	private FlyBehavior flyBehavior;
	private QuackBehavior quackBehavior;

	public void performQuack() {
		flyBehavior.quack();
	}
	public void performFly() {
		flyBehavior.fly();
	}
	public void swim() {

	}
	abstract void display();

	
	public void setFlyBehavior(FlyBehavior flyBehavior) {
		this.flyBehavior = flyBehavior;
	}
	public void setQuackBehavior(QuackBehavior quackbehavior) {
		this.quackBehavior = quackBehavior;
	}
}
class MallardDuck extends Duck {
	@Override
	public void display() {
		// 청둥오리
	}
}
class RedHeadDuck extends Duck {
	@Override
	public void display() {
		// 빨강머리오리
	}
}
class RubberDuck extends Duck {
	@Override
	public void display() {
		// 고무오리
	}
}
class DecoyDuck extends Duck {
	public void display() {
		// 모양 표현
	}
}
class UseDuck {
	public static void main(String[] args) {
		RedHeadDuck r = new RedHeadDuck();
	

		//날개를 달았다...
		r.setFlyBehavior(new FlyWithWing());
		r.performFly();
	}
}
