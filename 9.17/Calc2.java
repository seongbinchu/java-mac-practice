import java.util.Scanner;

class Calc2{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자");
		int a = scan.nextInt();
		System.out.println("숫자");
		int b = scan.nextInt();
		System.out.println(a/b);
		System.out.println(a%b);

	}
}