import java.util.Scanner;

	
class IfEx01{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("숫자를 일력하시오");
		int input = scan.nextInt();

		if(input > 5) {
			System.out.println("입력한 값은 5보다 큽니다");
		}else if(input == 4){
			System.out.println("입력한 값은 4입니다");
		}else if(input == 3 ){
			System.out.println("입력한 값은 3입니다");
		}else if(input == 2){
			System.out.println("입력한 값은 2입니다");
		}else if(input == 1){
			System.out.println("입력한 값은 1입니다");
		}else {
			System.out.println("입력한 값은 양수가 아닙니다");
		}

		System.out.println("end of main");

	}
} 