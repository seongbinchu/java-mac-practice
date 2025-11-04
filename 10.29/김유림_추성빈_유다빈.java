//객체 추가 고려 x 질럿,뮤탈,메딕만 존재한다고 가정
interface IMovable{
	void move();

	void patrol();
		
	void stop();
		
	void hold();
}
interface IAttack{
	void attack();
}
interface INoneAttack{
}
interface IUseMana{
	void skill();
}

public abstract class Unit implements IMovable{
	private int maxHp;
	private int hp;
	private int amr;
	private int sightRange;
	private int x;
	private int y;
	private int z;
	private double speed;
	private boolean flying;

	public Unit(int maxHp,int hp,int amr,int sightRange,double speed,boolean flying) {
		this.maxHp=hp;
		this.hp=hp;
		this.amr=amr;
		this.speed=speed;
		this.sightRange=sightRange;
		this.flying=flying;
		/*
		this.x;
		this.y;
		this.z;  생성건물이 지정한 생성 좌표값*/
	}
	
	public int getMaxHp(){
		return maxHp;
	}	
	public int getHp(){
		return hp;
	}
	public int getAmr(){
		return amr;
	}
	public int getSightRange(){
		return sightRange;
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public int getZ(){
		return z;
	}
	public double getSpeed(){
		return speed;
	}
	public boolean isFlying(){
		retunr flying;
	}
	public void setMaxHp(int maxHp){
		this.maxHp=maxHp;
	}
	public void setHp(int hp){
		this.hp=hp;
	}
	public void setAmr(int amr){
		this.amr=amr;
	}
	public void setSightRange(int sightRange){
		this.sightRange = sightRange;
	}
	public void setX(int x){
		this.x=x;
	}
	public void setY(int y){
		this.y=y;
	}
	public void setZ(int z){
		this.z=z;
	}
	public void setSpeed(double speed){
		this.speed=speed;
	}
	public void setFlying(boolean flying){
		this.flying=flying;
	}

	@Override
	public void move(int x, int y , int z , double speed){
		//flying == true일 경우 장애물 무시 speed 값 감속 가속
		//해당 x,y,z좌표로 이동
		//객체 혹은 땅 좌표 및 이동속도를 받아서
		//x,y,z축 기준 이동
	}
	@Override
	void patrol();{
		//현재위치저장
		int patrolX=this.x;
		int patrolY=this.y;
		int patrolZ=this.z; 
		while(//다른커맨드있을때까지){
			move(int x.int y,int z);
			move(int patrolX,int patrolY,int patrolZ);
	}
	@Override
	void stop(){
		//모든 기능정지
	}
	@Override			
	void hold(){
		//현재위치에서 무브정지 사거리내 적 공격
	}
}

public class Attacker extends Unit implements IAttack{
	private int damage;
	private int attackRange;
	private int attackCoolTime;
	
	public Attacker(int maxHp,int hp,int amr,int sightRange,boolean flying,
		double speed,int damage,int attackRange,int attackCoolTime){
		super(maxHp,hp,amr,sightRange,speed,flying);
		this.damage=damage;
		this.attackRange=attackRange;
		this.attackCoolTime=attackCoolTime;
	}

	public int getDamage(){
		return damage;
	}
	public int getAttackRange(){
		return attackRange;
	}
	public int getAttackCoolTime(){
		return attackCoolTime;
	}
	public void setDamage(int damage){
		this.damage=damage
	}
	public void setAttackRange(int attackRange){
		this.attackRange=attackRange;
	}
	public void setAttackCoolTime(int attackCoolTime){
		this.attackCoolTime=attackCoolTime;
	}


	@Override
	public void attack(int x,int y,int z){
		//공격력,사거리,공격속도에 따라 공격
		//파라미터 주소값까지 이동
		//파라미터 주소값에 unit 존재하는지 판별
		// 유닛존재 o
		//파라미터 flying값 판별
		//지상타입일시 지상타입공격 가능한지 판별
		//가능시 공격
		//공중타입일시 공중타입공격 가능한지 판별
		//가능시 공격
		// 유닛존재 x
		// 해당 주소값까지 움직이며 사거리 내 적 공격
	}

}

public class Zealot extends Attacker{

	private int shield;

	public Zealot(){
		super(maxHp,hp,amr,sightRange,speed,flying,groundDamage,attackRange,attackCoolTime);
		this.shield;
	}

	public int getShield(){
		return shield;
	}
	public void setShield(int shield){
		this.shield=shield;
	}

	@Override
	public void attack(int x,int y,int z){
		//2회타격
	}
}

public class Medic extends Unit implements IUseMana,INoneAttack{
	public static final int HEAL = 1;
	public static final int RESTORATION = 2;
	public static final int OPTICALFLARE = 3;

	private int maxMana;
	private int mana;
	
	public Medic(){
		super(maxHp,hp,amr,sightRange,speed,flying,groundDamage,attackRange,attackCoolTime,maxMana,mana);
		this.maxMana=maxMana;
		this.mana=mana;
	}

	public int getMaxMana(){
		return maxMana;
	}
	public int getMana(){
		return mana;
	}
	public void setMaxMana(int maxMana){
		this.maxMana=maxMana;
	}
	public void setMana(int mana){
		this.mana=mana;
	}

	@Override
	public void move(int x,int y,int z){
		//해당 좌표로 이동 후 사거리 내 체력이 소모된 유닛에게 HEAL
	}
	@Override
	public void hold(){
		//해당 좌표에서 사거리 내 체력이 소모된 유닛에게 HEAL
	}

	public void skill(int skillNum,Unit target){
		/*switch(num){
			case1: HEAL
			case2: RESTORATION
			case3: OPTICALFLARE
		}*/
	}
}
public class Mutalisk extends Attacker{

	public Mutalisk(maxHp,hp,amr,sightRange,speed,flying,groundDamage,attackRange,attackCoolTime){
		super();
		this.z=? // 공중유닛 z값 상수로 고정
	}

	public void recovery(){
		//체력 감소 있을 시 초당 회복
	}
	@Override
	public void attack(int x,int y,int z){	
		//뭉쳐 있는 경우 최대 3유닛 스플래시
	}
}