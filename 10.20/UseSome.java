class Some{

	private int num = 1;
	private String letter = "hi";
	// ��ü�� ������ ���ڿ��� ��ȯ�Ѵ�. �����ڰ� private��� ���� �� ����ڿ��� ���� ���� �����ؼ� toString�� ���ڿ��� ǥ���ϴ°�
	// ��ü�� ��� ��� ���� int,String,...���� ��� ���ڿ��� �ٲ�°� �ƴ�
	// ?? int
	// ����� ������ �� ����.
	public String toString(){
		String info = "return " + letter;
		return info;
	}
	public void print(){
		System.out.println(toString());
		System.out.println(this);
	}

	//this = > �ڽ� ��ü�� �ּҰ� = s 
 
}

class UseSome{
	public static void main(String[] args){
		Some s = new Some();

		System.out.println(s);
		System.out.println(s.toString()); //����
		String str = "String : "+s;
		System.out.println(str);

		s.print();
	}
}