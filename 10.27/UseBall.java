class Ball{
	private int num;
	private String color;
	public Ball(int num,String color){
		setNum(num);
		setColor(color);
	}
	public int getNum(int num){
		return num;
	}
	public void setNum(int num){
		this.num = num;
	}
	public void setColor(String color){
		this.color = color;
	}
	@Override
	public String toString(){
		return "Ball "+num + "("+color+")";
	}

	@Override
	public boolean equals(Object o){
		//번호가 같으면 같은 공이다
		if(o==null || !(o instanceof Ball)){
			return false;
		}
		Ball temp = (Ball)o;
		return num == temp.num && color.equals(temp.color);
		// String's eqauls는 문자열 같으면 true 리턴
	}
	
}

class UseBall{
	public static void main(String[] args){
		Ball b1 = new Ball(4,"red");
		Ball b2 = new Ball(5,"red");
		Ball b3 = new Ball(4,"black");
		Ball b4 = new Ball(5,"red");
		Ball b5 = new Ball(4,"red");
		System.out.println(b1.equals(null));
		System.out.println(b1.equals(new Pants()));
		System.out.println(b1.equals(b2));
		System.out.println(b1.equals(b3));
		System.out.println(b1.equals(b5));
	}
}