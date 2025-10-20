class Zealot{
	private String brood;
	private String roll;
	private String weapon;
	private int damage;
	private int hp;

	public String getBrood(){
		return brood;
	}
	public String getRoll(){
		return roll;
	}
	public String getWeapon(){
		return weapon;
	}
	public int getDamage(){
		return damage;
	}
	public int getHp(){
		return this.hp;
	}

	public void setBrood(String brood){
		this.brood = brood;
	}
	public void setRoll(String roll){
		this.roll = roll;
	}
	public void setWeapon(String weapon){
		this.weapon = weapon;
	}
	public void setDamage(int damage){
		this.damage = damage;
	}
	public void setHp(int hp){
		this.hp = hp;
	}
	public void takenAttack(){
		if(hp>damage){
			hp-=damage;
			System.out.println("���� "+damage+"�������� �ް��ֽ��ϴ�");
		}else if(hp-damage<=0){
			hp=0;
			System.out.println("...");
		}
		
	}

	public String toString() {
		String info = "���� : " + brood + "\n";
		info += "���� : " + roll + "\n";
		info += "����� : " + weapon + "\n";
		info += "�������� : " + damage + "\n";
		info += "ü���� : " + hp + "\n";
		return info;
	}

}

class UseZealot{
	public static void main(String[] args){

		Zealot z1 = new Zealot();
		System.out.println("My life for Aiur");

		z1.setBrood("protos");
		z1.setRoll("worrior");
		z1.setWeapon("sword");
		z1.setDamage(30);
		z1.setHp(100);
		System.out.println(z1);

		System.out.println("your base under the attack");
		while(z1.getHp()!=0){
			z1.takenAttack();
		}
	}
}
