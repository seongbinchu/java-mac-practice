class Some{
	public int num =2;
}
class Other{
	Some s = new Some();

	public Some getS(){
		return s;
	}
	public void print(){
		System.out.println(s.num);
	}
}
class UseOther{
	public static void main(String[] args){
		Other o = new Other();
		Some another=o.getS();
		another.num=100;

		o.print();
	}
}
