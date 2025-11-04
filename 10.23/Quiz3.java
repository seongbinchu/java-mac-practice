class Food{
	private int cal;
	private int price;
	private double weight;

	public Food(int cal,int price, double weight){
		setCal(cal);
		setPrice(price);
		setWeight(weight);
	}
	public String toString(){
		String info = "cal : "+cal+"\n";
		info+= "price : "+price+"\n";
		info+= "weight : "+weight+"\n";
		return info;
	}

	public int getCal(){
		return cal;
	}
	public int getPrice(){
		return price;
	}
	public double getWeight(){
		return weight;
	}
	public void setCal(int cal){
		this.cal = cal;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public void setWeight(double weight){
		this.weight= weight;
	}
}

class Melon{
	private String farm;
	public Melon(int cal,int price,double weight,String farm){
		super(cal,price,weight);
		setFarm(farm);
	}
	public String getFarm(){
		return farm;
	}
	public void setFarm(String farm){
		this.farm = farm;
	}
	public String toString(){
		String info=super.toString()+"\n";
		info+="farm : "+farm;
		return info;
	}
}

class Quiz3{
	public static void main(String[] args){
		Melon m1 = new Melon(123,1500,1.5,"º∫∫Û≥Û¿Â");
		//System.out.println(m1);
	}
}
