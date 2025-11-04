class Unit{
	private String name;
	private String location;
	private int hp;
	private int amr;
	private int attackPower;
	private int locationX;
	private int locationY;
	private boolean skyAttack;	 
	private boolean fly;	
	
	public String getName(){
		return name;
	}
	public int getHp(){
		return hp;
	}
	public int getAmr(){
		return amr;
	}
	public int getAttackPower(){
		return attackPower;
	}
	public boolean isSkyAttack(){
		return skyAttack;
	}
	public boolean isFly(){
		return fly;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setHp(int hp){
		this.hp=hp;
	}
	public void setAmr(int amr){
		this.amr=amr;
	}
	public void setAttackPower(int attackPower){
		this.attackPower = attackPower;
	}
	public void setSkyAttack(boolean skyAttack){
		this.skyAttack = skyAttack;
	}
	public void setFly(boolean fly){
		this.fly = fly;
	}
	public void setLocationX(int locationX){
		this.locationX=locationX;
	}
	public void setLocationY(int locationY){
		this.locationY=locationY;
	}
	public int moveX(int moveXLocation){
		return moveXLocation;
	}
	public int moveY(int moveYLocation){
		return moveYLocation;
	}
	public Unit attack(Unit another){
		return another;
	}
	@Override
	public String toString(){
		String info = "유닛명 : "+name+"\n";
		info+="체력 : "+hp+"\n";
		info+="방어력 : "+amr+"\n";
		info+="공격력 : "+attackPower+"\n";
		info+="현재위치 :"+locationX+","+locationY+"\n";
		info+="공중공격 가능 여부 : "+skyAttack+"\n";
		info+="공중유닛 : "+fly+"\n";
		return info;
	} 

	public Unit(String name,int hp,int amr,int attackPower,int locationX,int locationY,boolean skyAttack,boolean fly){
		setName(name);
		setHp(hp);
		setAmr(amr);
		setAttackPower(attackPower);
		setLocationX(locationX);
		setLocationY(locationY);
		setSkyAttack(skyAttack);
		setFly(fly);
	}
}
class TerranUnit extends Unit{
	
	private final int TERRAN_SUPPLY;
	public TerranUnit(String name,int hp,int amr,int attackPower,int locationX,int locationY,boolean skyAttack,boolean fly){
		super(name,hp,amr,attackPower,locationX,locationY,skyAttack,fly);
		TERRAN_SUPPLY=200;
	}
	public int getTERRAN_SUPPLY(){
		return TERRAN_SUPPLY;
	}
}
class Medic extends TerranUnit{

	private final int MEDIC_SUPPLY;

	public Medic(String name,int hp,int amr,int attackPower,int locationX,int locationY,boolean skyAttack,boolean fly){
		super(name,hp,amr,attackPower,locationX,locationY,skyAttack,fly);
		MEDIC_SUPPLY=1;
	}
	public int getMedicSupply(){
		return MEDIC_SUPPLY;
	}

	public TerranUnit heal(TerranUnit other){
		return other;
	}

	public String toString(){
		String info=super.toString();
		info+="인구수 : "+MEDIC_SUPPLY +"/"+getTERRAN_SUPPLY();
		return info;
	}
}

class ProtossUnit extends Unit {
	private final int PROTOSS_SUPPLY;
	private int shield;

	public ProtossUnit(String name,int hp,int amr,int attackPower,int locationX,int locationY,boolean skyAttack,boolean fly,int shield) {
		super(name,hp,amr,attackPower,locationX,locationY,skyAttack,fly);
		setShield(shield);
		PROTOSS_SUPPLY = 200;
	}

	public int getShield() {
		return shield;
	}
	public int getPROTOSS_SUPPLY(){
		return PROTOSS_SUPPLY;
	}
	public void setShield(int shield) {
		this.shield = shield;
	}
	
	@Override
	public String toString() {
		String info = super.toString();
		info += "쉴드 : " + getShield();

		return info;
	}
}

class Zealot extends ProtossUnit {
	private final int ZEALOT_SUPPLY;
	private int attackTimes;

	public Zealot(String name, int hp, int amr, int attackPower, int locationX, int locationY, boolean skyAttack,boolean fly, int shield, int attackTimes) {
		super(name,hp, amr, attackPower, locationX, locationY, skyAttack,fly, shield);
		setAttackTimes(attackTimes);
		ZEALOT_SUPPLY = 2;
	}

	public int getAttackTimes() {
		return attackTimes;
	}
	public void setAttackTimes(int attackTimes) {
		this.attackTimes = attackTimes;
	}

	@Override
	public String toString() {
		String info = super.toString() + "\n";
		info += "공격횟수 : " + attackTimes+"\n";
		info += "인구수 : "+ZEALOT_SUPPLY + "/" +getPROTOSS_SUPPLY();
		
		return info;
	}
}

class ZergUnit extends Unit{
	public Unit hpRecovery(Unit another){
		return another;
	}
	public ZergUnit(String name,int hp,int amr,int attackPower,int locationX,int locationY,boolean skyAttack,boolean fly){
		super(name,hp,amr,attackPower,locationX,locationY,skyAttack,fly);
	}

	public String toString(){
		String info = super.toString();
		return info;
	}
}
class Mutal extends ZergUnit {
	private final int MUTAL_SUPPLY;
	private boolean splash;
	
	public boolean isSplash(){
		return splash;
	}
	public void setSplash(boolean splash){
		this.splash = splash;
	}
	public Unit bounceAttack(Unit Target1,Unit Target2) {
		mutalAttack(Target1);
		mutalAttack(Target2);
	}
	public Unit mutalAttack(Unit Target1){
		int minusHp = attacPower-Mutal.bounceAttack();
		return Unit;
	}	

	public Mutal(String name,int hp,int amr,int attackPower, boolean SkyAttack,boolean fly,boolean splash){
		super(name,hp,amr,attackPower,SkyAttack,fly);
		setSplash(splash);
		MUTAL_SUPPLY = 2;
	}
	public String toString(){
		String info = super.toString();
		info +="인구수 : "+Mutal_SUPPLY + "/" +getZERG_SUPPLY();
	}
}

class UseUnit{
	public static void main(String[] args){
		Medic m1 = new Medic("메딕",60,0,0,1,1,false,false);
		System.out.println(m1);
		System.out.println("\n");
		Zealot z1 = new Zealot("질럿",100,2,16,1,1,false,false,60,2);
		System.out.println(z1);
		Mutal mutal = new Mutal("Mutal",160,1,9,true,true,true);

	}
}