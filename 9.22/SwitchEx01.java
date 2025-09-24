import java.util.Scanner;

class SwitchEx01{
	public static void main(String ags[]){
		Scanner scan = new Scanner(System.in);
	
		System.out.print("num : ");
		int num = scan.nextInt();

		switch(num) {
			case 1:
				System.out.println("one");
			case 2:
				System.out.println("two");
			case 3: 
				System.out.println("three");
				break;	
			default:
				System.out.println("error");	
	
		}
	}
}