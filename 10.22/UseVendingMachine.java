class Items{
	private String name;
	private String size;
	private int price;

	public String getName(){
		return name;
	}
	public String getSize(){
		return size;
	}
	public int getPrice(){
		return price;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setSize(String size){
		this.size = size;
	}
	public void setPrice(int price){
		this.price = price;
	}
	public Items(String name,String size,int price){
		this.name=name;
		this.size=size;
		this.price=price;
	}

	public String toString(){
		String info = "캐릭터명 : "+name+"\n";
		info += "사이즈 : "+size+"\n";
		info += "가격 : "+price;
		return info;
	}
}

class VendingMachine{
	private int moneyStock;              
	private int chunStock;	
 	private int gwakStock;
	private int peachStock;
	private int ryanStock;

	private int amount;

	private Items chunSik;
	private Items gwakChul;
	private Items peach;
	private Items ryan;

	public static final int CHUNSIK =1;
	public static final int GWAKCHUL = 2;
    	public static final int PEACH = 3;
    	public static final int RYAN = 4;


	public int getChunStock(){
		return chunStock;
	}
	public int getGwakStock(){
		return gwakStock;
	}
	public int getPeachStock(){
		return peachStock;
	}
	public int getRyanStock(){
		return ryanStock;
	}
	public int getMoneyStock(){
		return moneyStock;
	}
	public int getAmount(){
		return amount;
	}
	public void setChunStock(int chunStock){
		this.chunStock=chunStock;
	}
	public void setGwakStock(int gwakStock){
		this.gwakStock=gwakStock;
	}
	public void setPeachStock(int PeachStock){
		this.peachStock=peachStock;
	}
	public void setRyanStock(int RyanStock){
		this.ryanStock=ryanStock;
	}
	public void setMoneyStock(){
		this.moneyStock=moneyStock;
	}
	public void setAmount(){
		this.amount = amount;
	}
	public VendingMachine(){
		this.chunSik = new Items("춘식","중",3000);
		this.gwakChul = new Items("곽철","소",5000);
		this.peach = new Items("어피치","대",8000);
		this.ryan = new Items("라이언","대",10000);

		this.chunStock = 10;
    		this.gwakStock = 10;
    		this.peachStock = 10;
   		this.ryanStock = 10;
	// 생성자로 넣고 만들기 or setChunStock하면 재고 넣는 메소드라고 생각할 수 있나?
	// 스탁관리 메소드 만들기 or 생성자에서 관리
		this.moneyStock = 0;
		this.amount=0;
	}
	public void deposit(int won){
		if(won >0){
			this.amount += won;
		}else{
			System.out.println("잘못된 입력");
		}
	}
	public int change(){
		int change = this.amount;
		this.amount = 0;
		return change;
	}
	
	public Items select(int itemNum) {
		int price;
		Items select;	// 멤버변수로 잡아서 멤버변수에 리턴을 하고싶은데 왜 안될까
				// 케이스마다 재고랑 총액 검사하는데 중복 제거하는방법 뭘까
        	switch (itemNum) {
			case CHUNSIK:
                		price = this.chunSik.getPrice();
                		select = this.chunSik;
                
				if (this.chunStock <= 0 || this.amount < price) {
					return null;
				}
				this.chunStock--;
				break;
	
			case GWAKCHUL:
				/*if (this.gwakStock > 0 || this.amount >= price){
					price = this.gwakChul.getPrice();
					select = this.gwakChul;
				}*/ 	//초기화가 안되면 사용이 안된다 널값을 무조건 넣어놔야 돌아감
				price = this.gwakChul.getPrice();
				select = this.gwakChul;
				if (this.gwakStock <= 0 && this.amount < price) {
					return null;
				}
				this.gwakStock--;
				break;
                
			case PEACH:
				price = this.peach.getPrice();
				select = this.peach;
                
				if (this.peachStock <= 0 || this.amount < price) {
					return null;
				}
				this.peachStock--;
				break;

			case RYAN:
                		price = this.ryan.getPrice();
                		select = this.ryan;
                	
				if (this.ryanStock <= 0 || this.amount< price) {
					return null;
				}
				this.ryanStock--;
				break;
			default:
				return null;
		//null 아무것도 없음을 나타내는 값 선택조건 만족 x 면 null 
		//아니면 상품선택 메소드 실행이 아예안되게해야하나
 		}
	this.amount -= price;
	this.moneyStock += price;

	return select;
	}

	

}
class UseVendingMachine{
	public static void main(String[] args){
		VendingMachine m1 = new VendingMachine();
		m1.deposit(10000);

		Items select = m1.select(VendingMachine.CHUNSIK);
		m1.deposit(5000);
		System.out.println(m1.getAmount());
		int myChange = m1.change();

	}
}