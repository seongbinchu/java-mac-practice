
import java.util.Scanner;

class TodayQuiz1{
	public static void main(String args[]){
	
	int score = 85;
	String result = (score > 90) ? "A" : "B" ;
	System.out.println(result);

	}
}


// TodayQuiz2 ������ 4�� ����� �߰��� �ϴ°��� �ƴ� ��ɰ� ������ �ΰ� ������ ������ ���� ���̴°�
// �����丵�� �����̴� ������ �����ѹ��� ����� �ٲٴ°��� ���� �����丵�̾�?

// TodayQuiz3 ������ isSale�� �����ν� ���� �� �ִ� ���̱� �����̴�. isSale ��� false�� ���ٸ� 
// ���� ���� ����� �����ϰ����� isSale �� ������ false �ϼ��� true �ϼ��� ���� �𸥴�

// TodayQuiz4 �ɺ��� ����� 3.14 �̰� ���ͷ��� PI



class TodayQuiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���Ű����� �Է��Ͻÿ�");
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

		System.out.println("������ ���� ���� �ݾ��� " + result + "�Դϴ�");




	}
}