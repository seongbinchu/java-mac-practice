/*
	서점에 책장이 3개있고 각 책장에는 책이 10권 들어간다
*/
import java.util.Arrays;

class Book{
	private String title;
	private int price;

	public Book(String title,int price){
		setTitle();
		setPrice();
	}
	public String getTitle(String title){
		return title;
	}
	public int getPrice(int price){
		return price;
	}

	public void setTitle(){
		this.title=title;
	}
	public void setPrice(){
		this.price=price;
	}
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("Title : " + title +"\n");
		buf.append("Price : " + price +"\n");
		return buf.toString();
	}
}

class Bookcase{
	private Book[] books;// = new Book[10];
	private String category;
	
	public Bookcase(String category,Book... books){
		setCategory(category);
		setBooks(books);
	}

	public String getCategory(){
		return category;
	}
	public Book[] getBooks(){
		return books;
	}
	public void setCategory(String category){
		this.category=category;
	}
	public void setBooks(Book... books){
		this.books=books;
	}
	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("Category : "+ category +"\n");
		for(Book temp : books){
			buf.append(temp.toString() + "\n");
		}
		return buf.toString();
	}
}

class BookStore{
	private String name;
	private String tel;
	private Bookcase[] cases;

	public BookStore(String name,String tel,Bookcase... cases){
		setName(name);
		setTel(tel);
		setCases(cases);
	}

	public String getName(){
		return name;
	}
	public String getTel(){
		return tel;
	}
	public Bookcase[] getCases(){
		return cases;
	}
	public void setName(String name){
		this.name = name;
	}
	public void setTel(String tel){
		this.tel=tel;
	}
	public void setCases(Bookcase... cases){
		this.cases=cases;
	}

	@Override
	public String toString(){
		StringBuffer buf = new StringBuffer();
		buf.append("<< "+ name + "서점 >> \n");
		buf.append("-tel : "+ tel + "\n");
		for(Bookcase temp : cases){
			buf.append(temp.toString() + "\n");
		}
		return buf.toString();
	}
}
class UseBookstore{
	public static void main(String[] args){
		Bookcase case1 = new Bookcase("IT",
			new Book("이거슨 자바다",17000),
			new Book("이것도 자바다",15000),
			new Book("저것도 자바일까?",19000)
			);

		Bookcase case2 = new Bookcase("novel",
			new Book("거미",21000),
			new Book("어른왕자",18000),
			new Book("DNA",17000)
			);

		Bookcase case3 = new Bookcase("megazine",
			new Book("sunday busan",11000),
			new Book("막심",15000),
			new Book("나쁜생각",18000)
			);

		BookStore store = new BookStore("그린","051-900-1000",case1,case2,case3);
		System.out.println(store);
	}
}

//책의 정보를 알  수 없고 다차원배열 사용x