import java.util.Scanner;

class SaleTicket{

	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���̸� �Է��Ͻÿ�");
		int age = scan.nextInt();
		System.out.println( age > 19 || age < 65);

	}
}