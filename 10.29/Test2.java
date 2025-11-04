interface IAttackPower{
	int getAttackPower();	//데미지값 가지고옴
} 
class AttackPower implements IAttackPower{
	public int damage;		// 데미지 선언
	
	public AttackPower(int damage){
		this.damage = damage;	//데미지만 가진 객체 생성
	}
	@Override
	public int getAttackPower(){
		return damage;			//데미지 값 리턴하게
	}
}
class Test2{
	public static void todo(IAttackPower p){	
		System.out.println(p.getAttackPower());
	}
	public static void main(String[] args){
		todo(new AttackPower(10));		//10데미지가진 객체생성
	}
}
