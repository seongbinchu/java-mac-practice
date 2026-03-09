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

class NumberBookMap implements HumanManager{
	private Map<String, List<Human>> humanMap;

	public NumberBookMap(Human... humans){
		humanMap = new HashMap<>(); 
		for(Human h: humans){
			add(h); 
		}
	}
	
	public void add(Human h){
        String name = h.getName();
        
        List<Human> list = humanMap.get(name);
        
        if (list == null) {
            list = new ArrayList<>(); 
            humanMap.put(name, list); 
        }
        
        list.add(h);
	}

	public Human[] search(String name){
		List<Human> list = humanMap.get(name);
        
        if (list == null) {
            return new Human[0]; 
        }
        
        return list.toArray(new Human[0]);
	}

	public boolean delete(String name){
		boolean deleted = false; 
		int choice = -1;

		List<Human> box = humanMap.get(name);
		Scanner scan = new Scanner(System.in);

		if (box == null) {
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
				box.remove(choice-1);
				deleted = true;
			}else if(choice == 0){
				flag = false;
			}else{
				System.out.println("잘못된 번호 입력");
			}
			if (box.isEmpty()) {
				humanMap.remove(name);
				flag = false;
			}
		}
		return deleted;

	}

}

class UseNumberBookMap{
	public static void main(String[] args){
		HumanManager n1 = new NumberBookMap( 
			new Human("추성빈","mail1","tel1",29),
			new Human("강성빈","mail2","tel2",20),
			new Human("김성빈","mail3","tel3",27)
		);

		n1.add(new Human("테스트","mailtest","teltest",55));

		n1.search("강성빈");
	}
}