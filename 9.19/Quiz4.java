import java.util.Scanner;

class Quiz4{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자를 순서대로 입력하시오");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();

		int result = third;

		if(first>=second && first>=third){
			result = first;
		}else if(second >= first && second>=third){
			result = second;
		}
		System.out.println("가장 큰 숫자는 " +result);
	}
}


// && and      둘 다 참 일 시     ||  or    둘 중 하 나 라 도 참 일 시