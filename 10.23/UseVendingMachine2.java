class Drink{

	private String name; 
	private int price; 
	private int stock; 

	public Drink(String name, int price, int stock){
		setName(name);
		setPrice(price);
		setStock(stock);
	}

	public String getName(){
		return name;
	}

	public int getPrice(){
		return price;
	}

	public int getStock(){
		return stock;
	}

	public void setName(String name){
		this.name = name;
	}

	public void setPrice(int price){
		this.price = price;
	}

	public void setStock(int stock){
		this.stock = stock;
	}

	public String toString(){
		return name + "(" + price +"원)" + stock + "개";
	}
}


class VendingMachine{
	private int amount; 
	private Drink drink1;
	private Drink drink2;
	private Drink drink3; 
	public final static int DRINK1 = 1; 
	public final static int DRINK2 = 2;
	public final static int DRINK3 = 3;


	public VendingMachine(int amount, Drink drink1, Drink drink2, Drink drink3){
		this.amount = amount;
		this.drink1 = drink1;
		this.drink2 = drink2;
		this.drink3 = drink3;
	}

	public int getAmount(){
		return amount;
	}

	public Drink getDrink1(){
		return drink1;
	}

	public Drink getDrink2(){
		return drink2;
	}

	public Drink getDrink3(){
		return drink3;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}

	public void setDrink1(Drink drink1){
		this.drink1 = drink1;
	}

	public void setDrink2(Drink drink2){
		this.drink2 = drink2;
	}

	public void setDrink3(Drink drink3){
		this.drink3 = drink3;
	}


	public Drink saleDrink(int num){
		Drink drink = drink3;
		if(num == DRINK1){
				drink = drink1;
			}else if(num == DRINK2){
				drink = drink2;
		}
		drink.setStock(drink.getStock() - 1);
		amount -= drink.getPrice();
		System.out.println("음료수 " + drink.getName() + "을 판매하였습니다.");
		return drink;
	}


	public int returnCash(){
		System.out.println("거스름돈: " + amount + "원 반환되었습니다.");
		setAmount(0);
		return amount;
	}
}


class Customer{
	private int amount;
	
	public Customer(int amount){
		setAmount(amount);
	}

	public int getAmount(){
		return amount;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}


	public void inputCash(VendingMachine vm, int amount){
		vm.setAmount(vm.getAmount() + amount);
		System.out.println("잔액 " + amount + "원 넣으셨습니다.");
	}


	public void pushButton(VendingMachine vm, int button){
		button = vm.DRINK3;

		if(button == vm.DRINK1){
			button = vm.DRINK1;
		}else if(button == vm.DRINK2){
			button = vm.DRINK2;
		}else if(button == vm.DRINK3){
			button = vm.DRINK3;
		}else{
			System.out.println("버튼을 잘못 눌렀습니다.");
		}
		vm.saleDrink(button);
	}

}


class Manager{
	private int drinkStock; 
	private int amount; 
	private VendingMachine vm; 
	private Drink drink1;
	private Drink drink2;
	private Drink drink3;

	public Manager(int drinkStock, int amount){
		setDrinkStock(drinkStock);
		setAmount(amount);
	}

	public int getDrinkStock(){
		return drinkStock;
	}

	public int getAmount(){
		return amount;
	}

	public void setDrinkStock(int drinkStock){
		this.drinkStock = drinkStock;
	}

	public void setAmount(int amount){
		this.amount = amount;
	}


	public void refillDrink(int drinkNum, int stock){
		Drink drink = drink3;
		if(drinkNum == vm.DRINK1){
			drink = drink1;
		}else if(drinkNum == vm.DRINK2){
			drink = drink2;
		}else{
			System.out.println("잘못 선택하셨습니다.");
		}

		drink.setStock(drink.getStock() + stock);
		System.out.println("음료수" + drink.getName() + "의 재고가 " + stock + "만큼 채웠습니다.");
	}

	
	public void takeCash(VendingMachine vm){
		this.amount = vm.getAmount();
		vm.setAmount(0);
		System.out.println("자판기 잔액" + this.amount + "원을 벌었습니다.");
	}


	public void changePrice(int drinkNum, int price){
		Drink drink = drink3;
		if(drinkNum == vm.DRINK1){
			drink = drink1;
		}else if(drinkNum == vm.DRINK2){
			drink = drink2;
		}
		drink.setPrice(price);
		System.out.println("음료수" + drink.getName() + "의 가격이 " + price + "로 변경되었습니다.");
	}
}

class UseVendingMachine2{
	public static void main(String[] args) {
		Customer cu = new Customer(10000);
		Manager man = new Manager(18, 10000);
		Drink letsBe = new Drink("Let's Be", 1400, 5);
		Drink maximTOP = new Drink("Maxim TOP", 2000, 6);
		Drink water = new Drink("SamDaSoo", 150, 7);
		VendingMachine vm = new VendingMachine(0, letsBe, maximTOP, water);

		cu.inputCash(vm, 10000);
		//cu.pushButton(vm, 1);
		//vm.returnCash();
		//man.takeCash(vm);

		vm.saleDrink(3);
		vm.setDrink3(vm.saleDrink(1));
		vm.saleDrink(3);
		//리턴이 바로 drink로 나와서 drink3을 뽑은 후에 setDrink로 drink1값을 3으로 변경가능
	}
}