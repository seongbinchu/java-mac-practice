class First{
	private int num = 4;
	public int getNum(){
		return num;
	}
	protected void printNum(){
		System.out.println(num);
	}
}

class Second extends First{

}

class UseSecond{
	public static void main(String[] args){
		Second s = new Second();
		System.out.println(s.getNum());
		//System.out.println(s.num);	private 상속 제외
		s.printNum();
	}
}