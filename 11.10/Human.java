import java.util.*;
class MyUtils{
	public static int getRandomNumber(int from,int to){
		return (int)(Math.random() * (to - from+1)) + from;
	}
	public static char getRandomChar(){
		int num = getRandomNumber(0,1);
		char c;
		if(num == 0){
			c = (char)getRandomNumber('A','Z');
		}else{
			c = (char)getRandomNumber('a','z');
		}
		return c;
	}
	public static String getRandomString(int length){
		String str = "";
		for(int i=0 ; i<length;i++){
			str += getRandomChar();
		}
		return str;
	}
}
public class Human{
	private String name;
	private int age;
	private String mail;
	private String tel;

	public Human(){
		setName(MyUtils.getRandomString(3));
		setAge(MyUtils.getRandomNumber(10,40));
		int length = MyUtils.getRandomNumber(3,5);
		setMail(MyUtils.getRandomNumber(10,40)+"@doo.com");
		setTel(MyUtils.getRandomNumber(100,9999) + "-" +MyUtils.getRandomNumber(100,9999));
	}
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
	@Override
	public String toString(){
		return name + tel + mail + age;
	}
}
interface HumanManager {
	void add(Human h);
	Human[] search(String name);
	boolean delete(String name);
}

class PhoneBook implements HumanManager {
    private Hashtable<String, Vector<Human>> book;

    public PhoneBook() {
        book = new Hashtable<String, Vector<Human>>();
    }

    public void add(Human h) {
        String name = h.getName();
        Vector<Human> list;
        if (book.containsKey(name)) {
            list = book.get(name);
        } else {
            list = new Vector<Human>();
            book.put(name, list);
        }
        list.add(h);
    }

    public Human[] search(String name) {
        Vector<Human> list = book.get(name);
        Human[] arr;
        if (list == null) {
            arr = new Human[0];
        } else {
            arr = list.toArray(new Human[0]);
        }
        return arr;
    }

    public boolean delete(String name) {
        Vector<Human> list = book.get(name);

        if (list == null || list.size() == 0) {
            return false;
        } else {
            for (int idx = 0; idx < list.size(); idx++) {
                System.out.println((idx + 1) + ". " + list.get(idx));
            }
            System.out.print("삭제할 번호를 선택하세요: ");
            Scanner scan = new Scanner(System.in);
            int idx = Integer.parseInt(scan.nextLine()) - 1;
            list.remove(idx);
            return true;
        }
    }

    @Override
    public String toString() {
        Collection<Vector<Human>> values = book.values();
        String info = "";
        for (Vector<Human> list : values) {
            for (Human temp : list) {
                info += temp + "\n";
            }
        }
        return info;
    }
}

class PhoneBookTest {
    public static void main(String[] args) {
        PhoneBook pb = new PhoneBook();

        pb.add(new Human());
        pb.add(new Human());
        pb.add(new Human());
        pb.add(new Human("a", "abc@doo.org", "123-4567",20));
        pb.add(new Human("a", "abc@doo.org", "143-4567",21));
        pb.add(new Human("a", "abc@doo.org", "153-4567",22));
        pb.add(new Human("a", "abc@doo.org", "163-4567",23));

        pb.delete("a");

        Human[] arr = pb.search("a");

        for (Human h : arr) {
            System.out.println(h);
        }
    }
}

