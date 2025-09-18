
import java.util.Scanner;

class TodayQuiz1{
	public static void main(String args[]){
	
	int score = 85;
	String result = (score > 90) ? "A" : "B" ;
	System.out.println(result);

	}
}


// TodayQuiz2 정답은 4번 기능을 추가를 하는것이 아닌 기능과 동작은 두고 가독성 유연성 등을 높이는게
// 리팩토링의 목적이다 별개로 매직넘버를 상수로 바꾸는것이 좋은 리팩토링이야?

// TodayQuiz3 정답은 isSale은 변수로써 변할 수 있는 값이기 때문이다. isSale 대신 false가 들어갔다면 
// 오류 없이 출력이 가능하겠지만 isSale 은 변수라서 false 일수도 true 일수도 기계는 모른다

// TodayQuiz4 심볼릭 상수는 3.14 이고 리터럴은 PI



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
			sale = 0.5;
		}else if(paid > low){
			sale = 0.1;
		} sale=1;

		double result = paid * sale;

		System.out.println("고객님의 최종 결제 금액은 " + result + "입니다");




	}
}