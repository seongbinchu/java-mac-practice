class Car{
	private String name;
	private String color;
	private int price;

	public String getName(){
		return name;
	}
	public String getColor(){
		return color;
	}
	public int getPrice(){
		return price;
	}

	public void setName(String name){
		this.name = name;
	}
	public void setColor(String color){
		this.color = color;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public Car(String name,String color,int price){
		setName(name);
		setColor(color);
		setPrice(price);
	}
	public Car(String name,String color){
		this(name,color,2000);
	}
	public String toString(){
		String info = "model : "+name+"\n";
		info += "color : "+color+"\n";
		info += "price : "+price+"\n";
		return info;
	}

	public Car(String name,String color,int price){
		setCarInfo(String name,String color,int price);
	}
	public void setCarInfo(String name,String color,int price){
		setName(name);
		setColor(color);
		setPrice(price);
	} // this 는 첫번째 연산으로 무조건 들어가야 하기 때문에 해당 방법을 추가로 사용한다.
}

class UseCar{
	public static void main(String[] args){
		Car c1 = new Car();
		System.out.println(c1);
		Car c2 = new Car("ioniq","gray",5000);
		System.out.println(c2);
		Car c3 = new Car("testname","testcolor");
		System.out.println(c3);
	}
}
