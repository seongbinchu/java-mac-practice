import java.util.Scanner;
class Quiz7{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("첫번째 0을 제외하고 숫자를 한자리만 순서대로 기입하라");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();
		int fourth = scan.nextInt();
		int fiveth = scan.nextInt();

		int sum = first + second + third + fourth + fiveth;

		String result = "홀수";

		if(first == fiveth){
			result = "앞뒤 같음";
		}else if(sum%2 ==0){
			result = "짝수";
		}
		System.out.println(result);

	}
}