class First{
	private int num = 4;
}

class Second sxtends First{

}

class UseSecond{
	public static void main(String[] args){
		Seocnd s = new Second();
		System.out.println(s.num);
	}
}