import java.util.Scanner;

class Quiz3{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자를 순서대로 입력하시오");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();

		int result = third;

		if(first>=second){
			if(first>=third){
				result = first;
				}	
		}else if(second>=first){
			if(second>=third){
				result = second;
			}
		}
		System.out.println("가장 큰 값은 " + result);
	}
}

//삼항연산자로 가능한가?
//if(first>second && first>third) 가능