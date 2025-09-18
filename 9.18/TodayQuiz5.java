import java.util.Scanner;

class TodayQuiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("구매가격을 입력하시오");
		int paid = scan.nextInt();

		int high = 100000;
		int middle = 50000;
		int low = 10000;
		double sale;

		if(paid > high) {
			sale = 0.9;
		}else if(paid > middle){
			sale = 0.95;
		}else if(paid > low){
			sale = 0.99;
		}else{
			 sale=1;
		}
		double result = paid * sale;

		System.out.println("고객님의 최종 결제 금액은 " + result + "입니다");




	}
}