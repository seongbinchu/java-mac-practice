class TodayQuiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���Ű����� �Է��Ͻÿ�");
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
		System.out.println("������ ���� ���� �ݾ��� " + result + "�Դϴ�");




	}
}