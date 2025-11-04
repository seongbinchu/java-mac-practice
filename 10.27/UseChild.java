class Child extends Parent{
}
class Parent{
}
class UseChild{
	public static void main(String[] args){
		Child c = new Child();
		Parent p = c;
		Object o = c;
	}
}
