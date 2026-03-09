/*
	전화등록부 만들기
	이름으로 전화번호를 구분하여,동명이인의 문제를 해결한다.
	두가지 버전(Vector,Map)
*/
import java.util.*;
interface HumanManager {
	void add(Human h);
	Human[] search(String name);
	boolean delete(String name);
}
class Human {
	private String name;
	private int age;
	private String mail;
	private String tel;

	public String getName(){
		return name;
	}
	public String getMail(){
		return mail;
	}
	public String getTel(){
		return tel;
	}
	public int getAge(){
		return age;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setMail(String mail){
		this.mail=mail;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public void setAge(int age){
		this.age=age;
	}

	public Human(String name,String mail,String tel,int age){
		setName(name);
		setMail(mail);
		setTel(tel);
		setAge(age);
	}

	public Human(String name){
		setName(name);
	}
	public String toString(){
		String info = "name : "+name+"\n";
		info += "mail : "+mail+"\n";
		info += "tel : "+tel+"\n";
		info += "age : "+age+"\n";
		return info;
	}
}
class NumberBook implements HumanManager{
	Vector<Human> humans = new Vector<Human>();
	
	public Human[] getHumans(Human... humans){
		return humans;
	}
	public void setHumans(){
		this.humans = humans;
	}

	public NumberBook(Human... humans){
		this.humans = new Vector<Human>();
		for(Human h: humans){
			add(h);
		}
	}
	
	public void add(Human h){
		humans.add(h);
	}

	public Human[] search(String name){
		Vector<Human> result= new Vector<Human>();
		for(Human h : humans){
			if(h.getName().equals(name)){
				result.add(h);
			}
		}return result.toArray(new Human[0]);
		
	}
	public boolean delete(String name){
		Vector<Human> box = new Vector<Human>();
		int choice = -1;
		Scanner scan = new Scanner(System.in);
		boolean deleted = false; 

		for(Human h : humans){
			if(h.getName().equals(name)){
				box.add(h);
			}
		}
		if(box.isEmpty()){
			return false;
		}
		boolean flag = true;
		while(flag){
		
			for(int i=0; i < box.size(); i++){
				System.out.println("[" + (i+1) + "번]");
				System.out.println(box.get(i));
			}
			System.out.print("삭제할 사람의 번호를 입력하세요 (0: 종료)");
			choice = scan.nextInt();
			if(choice > 0 && choice <= box.size()){
				humans.remove(box.get(choice-1));
				box.remove(choice-1);
				deleted = true;
			}else if(choice == 0){
				flag = false;
			}else{
				System.out.println("잘못된 번호 입력");
			}
		
		}

		return deleted;
		
	}

	public void remove(int temp){
		humans.remove(temp);
	}

	public String toString(){
		String info="";
		for(Human h : humans){
			System.out.println(h);
		}
		return info;
	}

}
class UseNumberBook{
	public static void main(String[] args){
		NumberBook n1 = new NumberBook(
			new Human("추성빈","mail1","tel1",29),
			new Human("강성빈","mail2","tel2",20),
			new Human("김성빈","mail3","tel3",27),
			new Human("강성빈","mail4","tel4",20),
			new Human("김성빈","mail5","tel5",27),
			new Human("강성빈","mail6","tel6",20),
			new Human("김성빈","mail7","tel7",27),
			new Human("강성빈","mail8","tel8",20),
			new Human("김성빈","mail9","tel9",27),
			new Human("양성빈","mail10","tel10",25)
		);
		n1.delete("김성빈");
		//System.out.println(n1);
		
	}
}