/*
	서점에 책장이 3개있고 각 책장에는 책이 10권 들어간다
*/
import java.util.Arrays;

class Book{
	private String title;
	private int price;

	public Book(String title,int price){
		setTitle(title);
		setPrice(price);
	}

	public void setTitle(){
		this.title=title;
	}
	public void setPrice(){
		this.price=price;
	}
}


class Store{
	
	public static void main(String[] args){
		String[][] bookCase = new String[3][10];


		for(String[] temp : bookCase){
			System.out.println(Arrays.toString(temp));
		}
	}
}
