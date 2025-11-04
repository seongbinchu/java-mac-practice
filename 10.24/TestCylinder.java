class Circle{
	private double radius;
	private String color;

	public Circle(){
		this.radius=1.0;
		this.color="검정";
	}
	public Circle(double radius){
		this.radius= radius;
		this.color="검정";
	}
	public double getRadius(){
		return this.radius;
	}
	public String getColor(){
		return color;
	}
	public double getArea(){
		return 3.14*this.radius*this.radius;
	}
}

class Cylinder extends Circle{
	private double height;
	public Cylinder(){
		super();
		this.height=3.0;
	}
	public Cylinder(double radius){
		super(radius);
		this.height=3.0;
	}
	public Cylinder(double radius,double height){
		super(radius);
		this.height = height;
	}
	public double getHeight(){
		return height;
	}
	public double getVolume(){
		return getArea()*height;
		
	}
}

class TestCylinder{
	public static void main(String[] args){
		Cylinder obj1 = new Cylinder();
		Cylinder obj2 = new Cylinder(5.0,3.0);
		
		System.out.println(obj1.getRadius());
		System.out.println(obj1.getColor());
		System.out.println(obj1.getArea());
		System.out.println(obj1.getHeight());
		System.out.println(obj1.getVolume());

		System.out.println(obj2.getRadius());
		System.out.println(obj2.getColor());
		System.out.println(obj2.getArea());
		System.out.println(obj2.getHeight());
		System.out.println(obj2.getVolume());


	}
}