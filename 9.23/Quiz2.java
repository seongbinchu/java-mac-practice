import java.util.Scanner;
class Quiz2{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int input;
		do{
			System.out.println("����Է�");
			input = scan.nextInt();
		}while(input>=0);
		System.out.println(input);
	}
}
