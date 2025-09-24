import java.util.Scanner;
class Quiz6{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("숫자를 입력하시오");
		int first = scan.nextInt();
		int second= scan.nextInt();
		int third= scan.nextInt();

		String result = "삼각형이 아니다";
		
		int sum1 = first + second;
		int num2 = first + third;
		int num3 = second + third;

		if(sum1>third && num2>second &&num3>first ) {
			if( first == second && second == third ){
				result = "정삼각형";
			}else if(first == second || first == third || second == third){
				result = "이등변삼각형";
			}else{
				result = "일반삼각형";
			}
		}
		System.out.println(result);
	}
}
