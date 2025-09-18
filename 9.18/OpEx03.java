import java.util.Scanner;
class OpEx03{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자를입력하시오");

		int input = scan.nextInt();

		int point = 5;

		if(input > point){
			System.out.println("값이 " + point + " 보다 큽니다");
		}else {
			System.out.println("값이 " + point + " 보다 작습니다");
		}
		System.out.println("end of main");	
	}
}

/* 

import java.util.Scanner;
class OpEx03{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자를입력하시오");

		int input = scan.nextInt();

		if(input > point){
			System.out.println("값이 5보다 큽니다");
		}else {
			System.out.println("값이 5보다 작습니다");
		}
		System.out.println("end of main");	
	}
} 

5가 중복돼서 소스에 존재함 5를 수정하기 위해서는 코드를 여러개 수정해야함
때문에 5위치에 변수를 지정해줘야함

*/