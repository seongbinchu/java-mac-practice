class Zealot {
	private String brood;
	private String roll;
	private String weapon;
	private int attackDamage;
	private int hp;

	public String getBrood() {
		return brood; 
	}
	public String getRoll() {
		return roll;
	}
	public String getWeapon() {
		return weapon;
	}
	public int getAttackDamage() {
		return attackDamage;
	}
	public int getHp() {
		return hp; 
	}

	public void setBrood(String brood) {
		this.brood = brood;
	}
	public void setRoll(String roll) {
		this.roll = roll;
	}
	public void setWeapon(String weapon) {
		this.weapon = weapon;
	}
	public void setAttackDamage(int attackDamage) {
		this.attackDamage = attackDamage;
	}
	public void setHp(int hp) {
		this.hp = hp;
	}

	public void takeDamage(int damage) {
		if ((this.hp-damage) > 0) {
			this.hp -= damage;
			System.out.println("해당 유닛이 "+damage +"데미지를 받고있습니다(남은 체력: " + this.hp + ")");
       		 
       			if ((this.hp-damage) <= 0) {
				this.hp = 0;
				System.out.println("...");
			}
        	}
	}

}

class UseZealot {
	public static void main(String[] args) {

		Zealot z1 = new Zealot();
		System.out.println("My life for Aiur!");

		z1.setBrood("Protoss");
 		z1.setRoll("Warrior");
        	z1.setWeapon("Psi Blades");
        	z1.setAttackDamage(16);
        	z1.setHp(100);

        	System.out.println("Your bass is under attack");

        	while(z1.getHp()!=0){
			z1.takeDamage(30);
		}

    }
}