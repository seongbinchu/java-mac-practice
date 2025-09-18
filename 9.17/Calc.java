import java.util.Scanner;
class Calc{
	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		System.out.println("정수를 입력하시오");
		int A = scan.nextInt();
		System.out.println("정수를 입력하시오");
		int B = scan.nextInt();
		System.out.println("몫은 = " + A/B);
		System.out.println("나머지는 = "+ A%B);
	}
}

