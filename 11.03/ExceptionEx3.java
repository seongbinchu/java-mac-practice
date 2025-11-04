class LikeAnimalException extends Exception{
	public LikeAnimalException(){
		super("철컹철컹");
	}
}
class Human{
	private String name;
	private int age;
	
	public Human(String name,int age){
		setName(name);
		setAge(age);
	}
	public String getName(){
		return name;
	}
	public int getAge(){
		return age;
	}
	public void setName(String name){
		this.name=name;
	}
	public void setAge(int age){
		this.age=age;
	}

	public void love(Human honey) throws LikeAnimalException{
		if(Math.abs(age - honey.getAge()) < 20 ){
			System.out.println("name"+ "♥"+honey.getName());
			//참일경우 love에서 행동지정
		}else{
			throw new LikeAnimalException();
		}
	}

}
class ExceptionEx3{
	public static void main(String[] args){
		Human h1 = new Human("뮤",18);
		Human h2 = new Human("한유진",19);
		Human h3 = new Human("영욱",49);
		Human h4 = new Human("test",30);
		try{
			h2.love(h1);
		}catch(LikeAnimalException e){
			System.out.println(e.getMessage());
		}

		
		try{
			h3.love(h2);
		}catch(LikeAnimalException e){
			System.out.println(e.getMessage());
			//거짓일 경우 메소드 부른쪽이 행동지정
		}
	}
}