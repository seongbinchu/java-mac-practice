import java.util.*;
class MyUtils {

    public static int getRandomNumber(int from, int to) {
        return (int)(Math.random() * (to - from + 1)) + from;
    }

    public static char getRandomChar() {
        int num = getRandomNumber(0, 1);
        char c;
        if(num == 0) {
            c = (char)getRandomNumber('A', 'Z');
        } else {
            c = (char)getRandomNumber('a', 'z');
        }
        return c;
    }

    public static String getRandomString(int length) {
        String str = "";
        for(int i=0; i<length; i++) {
            str += getRandomChar();
        }
        return str;
    }
}
public class Human {
    private String name;
    private int age;
    private String mail;
    private String tel;

    // for test
    public Human() {
        setName(MyUtils.getRandomString(3));
        setAge(MyUtils.getRandomNumber(10, 40));
        int length = MyUtils.getRandomNumber(3, 5);
        setMail(MyUtils.getRandomNumber(10, 40) + "@doo.com");
        setTel(MyUtils.getRandomNumber(100, 9999) + "-" + MyUtils.getRandomNumber(100, 9999));
    }

    public Human(String name) {
        setName(name);
    }

    public Human(String name, int age, String mail, String tel) {
        setName(name);
        setAge(age);
        setMail(mail);
        setTel(tel);
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getMail() {
        return mail;
    }

    public String getTel() {
        return tel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }
	    @Override
    public String toString() {
        return name + "(" + tel + ", " + mail + ", " + age + ")";
    }

    @Override
    public boolean equals(Object o) {
        if(o == null || !(o instanceof Human)) {
            return false;
        }

        Human temp = (Human)o;
        return name.equals(temp.getName());
    }
}
interface HumanManager {
	void add(Human h);
	Human[] search(String name);
	boolean delete(String name);
}
class PhoneBook implements HumanManager{
	private Vector<Human> list;

	public PhoneBook(){
		list = new Vector<Human>();
	}
	public void add(Human h){
		list.add(h);
	}
	public boolean delete(String name){
		Integer[] indices = getIndices(name);
		if(indices.length = 0){
			int num = 1;
			for(int idx : indices){
				Human temp = list.get(idx);
				System.out.println(num ". "+temp.toString());
				num++;
			}
			System.out.println("삭제할 번호");
			Scanner scan = new Scanner(System.in);
			int deleteIdx = scan.nextInt() - 1;
			list.remove(indices[deleteIdx].inValue());
			return true;
		}else{
			return false;
		}
	}
	
	private Integer[] getIndices(String name){
		Human target = new Human(name);

		boolean flag = treu;
		int idx = 0;
		Vector<Integer> indices = new Vector<Integer>();
		while(flag){
			idx = list.indexOf(target, idx);
			if(idx == -1){
				flag = false;
			}else{
				indices.add(idx);
				idx++;
			}
		}
		return indices.toArray(new Integer[0]);
	}
}
class PhoneBookTest{
	public static void main(String[] args){
		phoneBook pb = new PhoneBook();

		pb.add(new Human());
		pb.add(new Human());
		pb.add(new Human());
		pb.add(new Human("a","abc@doo.org","123-4567",20));
	}
}