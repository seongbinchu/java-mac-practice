/*
	ÀüÈ­µî·ÏºÎ ¸¸µé±â
	ÀÌ¸§À¸·Î ÀüÈ­¹øÈ£¸¦ ±¸ºĞÇÏ¿©,µ¿¸íÀÌÀÎÀÇ ¹®Á¦¸¦ ÇØ°áÇÑ´Ù.
	µÎ°¡Áö ¹öÀü(Vector,Map)
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
		Human[] result= new Human[1];
		for(Human h : humans){
			if(h.getName().equals(name)){
				result=new Human[]{h};
			}
		}return result;
		
	}
	public boolean delete(String name){
		Vector<Human> box = new Vector<Human>();

		boolean deleted =false;
		int choice = -1;
		int count=0;

		for(Human h : humans){
			if(h.getName().equals(name)){
				box.add(h);
				System.out.print(h);
				System.out.println("index : "+humans.indexOf(h));
				count++;
			}
		}
		for(int i =0;i<count||choice!=0;i++)
			Scanner scan = new Scanner(System.in);
			System.out.println("¼ıÀÚ¸¦ ÀÔ·ÂÇÏ¼¼¿ä. 0:ÅğÀå");
			choice = scan.nextInt();
			remove(choice);
		}deleted = true;
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
			new Human("Ãß¼ººó","mail1","tel1",29),
			new Human("°­¼ººó","mail2","tel2",20),
			new Human("±è¼ººó","mail3","tel3",27),
			new Human("°­¼ººó","mail4","tel4",20),
			new Human("±è¼ººó","mail5","tel5",27),
			new Human("°­¼ººó","mail6","tel6",20),
			new Human("±è¼ººó","mail7","tel7",27),
			new Human("°­¼ººó","mail8","tel8",20),
			new Human("±è¼ººó","mail9","tel9",27),
			new Human("¾ç¼ººó","mail10","tel10",25)
		);
		n1.delete("±è¼ººó");
		//System.out.println(n1);
		
		
		
	}
}