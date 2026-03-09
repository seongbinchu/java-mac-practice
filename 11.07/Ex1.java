class Some{

}
class Ex1{
	public static void main(String[] args){
		Some s1 = new Some();
		Some s2 = new Some();
		
		System.out.println(s1);
		System.out.println(s2);
		System.out.println(Integer.toHexString(s1.hashCode()));
		System.out.println(Integer.toHexString(s2.hashCode()));
	}
}
