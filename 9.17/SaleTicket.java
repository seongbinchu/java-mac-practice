import java.util.Scanner;

class SaleTicket{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("나이를 입력하시오");
		int age = scan.nextInt();
		System.out.println( age > 19 || age < 65);

	}
}