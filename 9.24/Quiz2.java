import java.util.Scanner;

class Quiz2{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		int output;
		do{

			System.out.println("����");
			int input = scan.nextInt();

			System.out.println("����");
			int input2 = scan.nextInt();

			int sum = input + input2;
			System.out.println(sum);

			System.out.println("0(����) or 1(���)");
			output = scan.nextInt();
		}while(output ==1);
		
		System.out.println("����");
				
	}
}