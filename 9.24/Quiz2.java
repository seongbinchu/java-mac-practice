import java.util.Scanner;

class Quiz2{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		int output;
		do{

			System.out.println("정수");
			int input = scan.nextInt();

			System.out.println("정수");
			int input2 = scan.nextInt();

			int sum = input + input2;
			System.out.println(sum);

			System.out.println("0(종료) or 1(계속)");
			output = scan.nextInt();
		}while(output ==1);
		
		System.out.println("종료");
				
	}
}