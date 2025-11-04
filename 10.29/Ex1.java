interface IA{
	int getNum();
}
class Some implements IA{
	public int num;

	public Some(int num){
		this.num=num;
	}
	@Override
	public int getNum(){
		return num;
	}
}
class Ex1{
	public static void todo(IA a){
		System.out.println(a.getNum());
	}
	public static void main(String[] args){
		todo(new Some(10));
	}
}
// 이거아니면 추상클래스