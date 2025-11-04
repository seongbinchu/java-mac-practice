interface IWalking{
	void walk();
}
class Human{

}
class Dead extends Human implements IWalking{
	@Override
	public void walk(){
		System.out.println("walk");
	}
}
class InterfaceEx4{
	public static void main(String[] args){
		Dead d1 = new Dead();
		d1.walk();
	}
}
//보통의 클래스 형태 상속받고 구현
//상속 확장하다?