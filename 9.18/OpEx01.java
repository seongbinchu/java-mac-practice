import java.util.Scanner;

class OpEx01 {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("a :");
		int a = scan.nextInt();
		
		System.out.println("b :");
		int b = scan.nextInt();

		String result = "a 가 b 보다";

		result += (a > b ) ? "크다" : "크지않다";
		
		System.out.println(result);

	}
}