import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		long input = scan.nextLong();

		if(input % 2 == 0){
			System.out.println("짝수입니다");
			}else if(input % 2 > 0){
			System.out.println("홀수입니다");
			}
	}
}

//정수는 짝수 아님 혹수인데 왜 else if 를 만들었니 else 만있으면 된다.

/* import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		long input = scan.nextLong();

		if(input % 2 == 0){
			System.out.println("짝수입니다");
			}else
			System.out.println("홀수입니다");
			}
	}
}
*/

짝수 혹은 홀수를 출력 시키는 값들은 문자열이다. 때문에 String result 를 통해 줄일 수 있음

import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자를 입력하세요");
		long input = scan.nextLong();

		String result = "홀수";

		if(input % 2 == 0){
				result = "짝수";
			}else 
			System.out.println(result);
	}
}
