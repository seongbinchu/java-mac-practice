//사람 여려명 넣고 이름으로 정렬
import java.util.Arrays;
class Human implements Comparable<Human>{
	private String name;
	private int age;

	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setAge(int age){
		this.age = age;
	}
	public Human(String name,int age){
		setName(name);
		setAge(age);
	}
	public Human(String name){
		setName(name);
	}
	@Override
	public int compareTo(Human otherHuman){
		String otherHumanName = otherHuman.getName();
		int result = name.compareTo(otherHumanName); //* -1; 하면 내림차순
		if(result ==0){
			result = age - otherHuman.getAge();
		}
		return result;
		//name과 age 를 이용하고 있음
		//compareTo를 사용할 때는 name 과 age가 정확히 정의되어야 함(식별자)
		//식별자(필수) => name 과 age 를 기준으로 기능함?
		//equals 마찬가지
	}
	@Override
	public String toString(){
		String info = "name : "+name+" age : "+age+"\n";
		return info;
	}
	
}
class UseHuman{
	public static void main(String[] args){
		Human[] arr = {
			new Human("A",20),
			new Human("H",22),
			new Human("E",30),
			new Human("T",41),
			new Human("C",23),
			new Human("B",55),
			new Human("D",27),
			new Human("T",27) //동명이인 정렬불가능 다음기준 필요
		};

		Arrays.sort(arr);
		System.out.println(Arrays.toString(arr));
		//int idx = Arrays.binarySearch(arr, new Human("C"));	
		//binarySearch 기준이 compareTo 값이 0 이면 같은것 
		//현재 0일 때 나이로 한번 더 비교하기 때문에 값이 안나옴
		int idx = Arrays.binarySearch(arr, new Human("C",23));
		System.out.println(idx);
	}
}
