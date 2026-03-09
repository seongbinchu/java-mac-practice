import java.util.*;

class Car{
	public static final boolean HANDICAPPED = true;
	private String number;
	private boolean handicapped;

	public Car(String number){
		setNumber(number);
	}
	public Car(String number,boolean handicapped){
		setNumber(number);
		setHandicapped(handicapped);
	}
	public String getNumber(){
		return number;
	}
	public boolean isHandicapped(){
		return handicapped;
	}
	public void setNumber(String number){
		this.number = number;
	}
	public void setHandicapped(boolean handicapped){
		this.handicapped = handicapped;
	}
	@Override
	public String toString(){
		String kind = handicapped ? "장애인차량" : "일반차량";
		return number + "(" + kind + ")";
	}

	@Override
	public boolean equals(Object o){
		if(o == null || !(o instanceof Car)){
			return false;
		}
		Car temp = (Car)o;
		return number.equals(temp.getNumber());
	}
}
class ParkingArea{
	private static int no =1;
	private int areaNo;
	private Car car;
	private boolean kind;

	public ParkingArea(boolean kind){
		setKind(kind);
		areaNo = no;
		no++;
	}

	public Car getCar(){
		return car;
	}
	public int getAreaNo(){
		return areaNo;
	}
	public void setCar(Car car){
		this.car=car;
	}
	public void setKind(boolean kind){
		this.kind = kind;
	}
	public void setAreaNo(int areaNo){
		this.areaNo = areaNo;
	}
	public boolean isEmpty(){
		return car == null;
	}
	public Car out(){
		Car temp = car;
		car = null;
		return temp;
	}

	public boolean in(Car car){
		if(isEmpty()){ //비었냐?
			if(!car.isHandicapped()){ //장애인 차량이 아닌가?
				if(kind == Car.HANDICAPPED){ //장애인구역인가?
					System.out.println("비장애인은 장애인구역에 주차할 수 없습니다");
					return false;
				}else{
					this.car=car;
					return true;
				}
			}else{
				this.car = car;
				return true;
			}
		}else{
			return false;
		}
	}
	@Override
	public boolean equals(Object o){
		if(o==null || !(o instanceof ParkingArea)){
			return false;
		}
		ParkingArea temp = (ParkingArea)o;
		return areaNo == temp.getAreaNo();
	}

	@Override
	public String toString(){
		String info = areaNo +"번 ";
		info +="주차구역(장애인전용 : "+kind+") - ";
		if(car == null){
			info += "비어있음";
		}else{
			info += car + "주차 중";
		}
		return info;
	}
}

class ParkingInfo implements Comparable<ParkingInfo>{
	private String number;
	private int areaNo;

	public ParkingInfo(String number){
		setNumber(number);
	}

	public ParkingInfo(String number,int areaNo){
		setNumber(number);
		setAreaNo(areaNo);
	}
	public String getNumber(){
		return number;
	}
	public int getAreaNo(){
		return areaNo;
	}
	public void setNumber(String number){
		this.number= number;
	}
	public void setAreaNo(int areaNo){
		this.areaNo = areaNo;
	}
	@Override
	public int compareTo(ParkingInfo info){
		return number.compareTo(info.getNumber());
	}
	@Override
	public String toString(){
		return areaNo +"(" + number +")";
	}
}

class ParkingLot{
	public static final int OUT_OF_RANGE = -1;
	public static final int MAX_TRY = 5;

	private ArrayList<ParkingInfo> info;
	private ParkingArea[] units;
	private int currentCount;
	private final int MAX_COUNT;
	private Random random;

	public ParkingLot(int max,int countOfHandicapped){
		if(countOfHandicapped > max){
			System.out.println(
				"최대 주차수보다 장애인 전용 주차수가 더 많을 수 없습니다."	
			);
			System.exit(OUT_OF_RANGE);
		}
		MAX_COUNT = max;

		units = new ParkingArea[MAX_COUNT];
		for(int i= 0; i<MAX_COUNT;i++){
			boolean isHandicapped = false;
			if(i < countOfHandicapped){
				isHandicapped = Car.HANDICAPPED;
			}
			units[i] = new ParkingArea(isHandicapped);
		}

		info = new ArrayList<ParkingInfo>();
		random = new Random();
	}

	public boolean in(Car car){
		System.out.println(car + "주차시도 중...");
		if(currentCount < MAX_COUNT){
			int tryCount = 0;
			int idx;
			do{
				idx = random.nextInt(MAX_COUNT);			// 0 이 나올 수 있음
				tryCount++;
			}while(!units[idx].in(car) && tryCount < MAX_TRY); // 배열은 인덱스가 0부터 시작하니 

			if(tryCount < MAX_TRY){
				ParkingInfo temp = new ParkingInfo(				// areanum = >1더해줘야함
					car.getNumber(),idx+1	
				);
				info.add(temp);
				currentCount++;
				System.out.println(
					"주차완료 ("+car.getNumber() + " / " +
					temp.getAreaNo() + "번 구역 ) : 남은자리 (" +
					(MAX_COUNT - currentCount) +"개)"
				);

				return true;
			}else{
				System.out.println("주차자리를 못찾고 떠나갑니다("+car+")");
				return false;
			}
		}else {
			System.out.println("죄송합니다 만차입니다.");
			return false;
		}
	}
	public Car out(String number){
		Car car = null;
		Collections.sort(info);
		int idx = Collections.binarySearch(
			info,new ParkingInfo(number)	
		);
		if(idx >=0){								//존재하지않는다면 -1
			ParkingInfo temp = info.get(idx);
			car = units[temp.getAreaNo() -1].out();	//인덱스니까 -1
			info.remove(temp);						//차량번호 지우기
			currentCount--;
			System.out.println(
				"출차완료("+number+")"
			);
		}else{
			System.out.println(
				"해당 차량이 존재하지 않습니다.("+number+")"	
			);
		}
		return car;
	}

	@Override
	public String toString(){
		String info = "<< 주차정보 >> \n";
		for(ParkingArea temp : units){
			info +=temp+"\n";
		}
		info +="주차 가능 차량 수 : "+ (MAX_COUNT - currentCount);
		return info;
	}
	
}
class TestParkingLot{
	public static void main(String[] args){
		ParkingLot lot = new ParkingLot(9,2);

		Car[] cars = {
			new Car("3가7234",Car.HANDICAPPED),
			new Car("2가1234",Car.HANDICAPPED),
			new Car("7다3234"),
			new Car("5마7544"),
			new Car("9나3156"),
			new Car("3사7651"),
			new Car("7아3248"),
			new Car("9라0801"),
			new Car("1바5647",Car.HANDICAPPED)
		};
		for(Car temp:cars){
			lot.in(temp);
		}
		lot.out("7아3248");
		System.out.println(lot);
		lot.out("9나3156");
		System.out.println(lot);
		lot.out("3가7234");
		System.out.println(lot);
		

	}
}
