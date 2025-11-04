class Zealot{
	int hp = 100;
	int power = 10;
	String name;
	public Zealot(String name){
		this.name = name;
	}
	public void attack(Zealot target){
		if(hp ==0){
			System.out.println("죽은 유닛 공격 불가");
		}else if(target.hp ==0){
			System.out.println("대상 죽음");
		}else{
			if( (int)(Math.random()*5)+1 !=1){
				target.hp -=power;
				System.out.println(target.name+"데미지 입음 남은 체력 :"+ target.hp);
			}else{
				System.out.println("공격 빗나감");
			}
			if(target.hp <=0){
				System.out.println("타겟사망");
				target.hp=0;
			}else{
				target.attack(this);
				System.out.println(target.name+"데미지 입음 남은 체력 :"+this.hp);
			}
		}
	}
}
class ZealotTest{
	public static void main(String[] args){
		Zealot z1 = new Zealot("삼식이");
		Zealot z2 = new Zealot("두식이"); 
		z1.attack(z2);
	}
}
