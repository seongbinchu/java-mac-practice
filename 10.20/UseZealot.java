class Zealot{
	private String brood;
	private String roll;
	private String weapon;
	private int damage;
	private int hp;
	private int speed;		
	private int locationX;	//가로축 위치
	private int locationY;	//세로축 위치

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
		return hp;
	}
	public int getSpeed(){
		return speed;
	}
	public int getLocationX(){
		return locationX;
	}
	public int getLocationY(){
		return locationY;
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
	public void setSpeed(int speed){
		this.speed = speed;
	}
	public void setLocationX(int locationX){
		this.locationX = locationX;
	}
	public void setLocationY(int locationY){
		this.locationY = locationY;
	}

	public String toString() {
		String info = "종족 : " + brood + "\n";
		info += "역할 : " + roll + "\n";
		info += "무기 : " + weapon + "\n";
		info += "공격력 : " + damage + "\n";
		info += "체력 : " + hp + "\n";
		info += "이동속도 : "+speed+"\n";
		info += "위치 : (" + locationX +","+ locationY + ")\n";
		return info;
	}

	public Zealot(String brood, String roll, String weapon, int damage, int hp, int speed, int locationX, int locationY){
		this.brood = brood;
        	this.roll = roll;
		this.weapon = weapon;
		this.damage = damage;
		this.hp = hp;
		this.speed = speed;
		this.locationX = locationX;
		this.locationY = locationY;
	}
	
	public Zealot(){
		setBrood("protos");
		setRoll("worrior");
		setWeapon("Psionic blade");
		setDamage(30);
		setHp(100);
		setSpeed(1);
		setLocationX(-100);
		setLocationY(-100);
	}

	public Zealot(int locationX,int locationY){
		this("protos", "worrior", "Psionic blade", 30, 100, 1, locationX, locationY);
	}
	
	public void moveX(int time){
		int range = calcRange(time);
		this.locationX += range;
	}
	public void moveY(int time){
		int range = calcRange(time);
		this.locationY += range;
		
	}
	private int calcRange(int time){
		int range = this.speed*time;
		return range;
	}

	public void attackUnit(Zealot other){
		if(other.hp>this.damage){
			other.hp-=this.damage;
		}else{
			other.hp=0;
		}
	}
}

class UseZealot{
	public static void main(String[] args){

		Zealot z1 = new Zealot();
		Zealot other = new Zealot(100,100);
		
		System.out.println(z1);
		System.out.println(other);

		z1.moveX(100);
		z1.moveY(100);
		other.moveX(-99);
		other.moveY(-99);

		z1.attackUnit(other);
		System.out.println(other);
	}
}