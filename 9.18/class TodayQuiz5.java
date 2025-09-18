class TodayQuiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("구매가격을 입력하시오");
		int paid = nextInt();

		int high = 100000;
		int middle = 50000;
		int low = 10000;
		double sale;

		double result = paid * sale;

		if(paid > high) {
			sale = 0.9;
		}else if(paid > 50000){
			sale = 0.5;
		}else if(paid > 10000){
			sale = 0.1;
		} sale=1;
		System.out.println("고객님의 최종 결제 금액은 " + result + "입니다");




	}
}