class Human{
	private String name;
	private int age;

	public String getName(){
		return name;
	}
	public void setName(String newName) {
		name = newName;
	}
}
class UseHuman{
	public static void main(String args[]){
		Human h = new Human();
		h.name = "己后";
		h.setName = "己后";
		System.out.println(h.setName);
	}
}