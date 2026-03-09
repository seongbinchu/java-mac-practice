import java.util.*;

class MyKey{
	private int num;
	private String str;

	public MyKey(int num,String str){
		setNum(num);
		setStr(str);
	}
	public MyKey(int num){
		setNum(num);
	}

	public int getNum(){
		return num;
	}
	public String getStr(){
		return str;
	}
	public void setNum(int num){
		this.num=num;
	}
	public void setStr(String str){
		this.str=str;
	}

	public String toString(){
		return str + "("+num+")";
	}
	@Override
	public boolean equals(Object o){
		if(o == null|| !(o instanceof MyKey)){
			return false;
		}
		// hashCode를 오버라이드 한 경우에만 적용할 것!!
		if(hashCode() !=o.hashCode()){
			return false;
		}
		MyKey key = (MyKey)o;
		return num == key.getNum();
	}
	/*
		a.b 두 객체가 있을 때,

		a.equals(b) == true 인 경우
		반드시 a.haseCode()==b.haseCode() 가 보장되야한다.
		역은 성립 하지않는다.

		멤버 변수 중 equals에서 사용하는 멤버 변수만 hashCode에서 연산의 대상이 될 수 있다
	*/
	@Override
	public int hashCode(){
		return num % 3;
	
	}
}
class MapEx2{
	public static void main(String[] args){
		Hashtable<MyKey,String> map = new Hashtable<MyKey,String>();

		map.put(new MyKey(1,"a"),"A");
		map.put(new MyKey(2,"b"),"B");
		map.put(new MyKey(3,"c"),"C");
		map.put(new MyKey(4,"d"),"D");
		map.put(new MyKey(5,"e"),"E");
		map.put(new MyKey(6,"f"),"F");
		map.put(new MyKey(7,"g"),"G");
		
		System.out.println(map.get(new MyKey(3)));		//equals override하고 생성자 만들어줘도 null
	}
}
/*
Hash...
해싱= 빨리찾는기법
hashCode= 기본 객체주소값
null인이유 해쉬코드(객체주소)가 일치하는것이 없기때문
hashCode 검사 => eqauls검사 => 발견
*/