import java.util.Scanner;

class Quiz4{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("���ڸ� ������� �Է��Ͻÿ�");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();

		int result = third;

		if(first>=second && first>=third){
			result = first;
		}else if(second >= first && second>=third){
			result = second;
		}
		System.out.println("���� ū ���ڴ� " +result);
	}
}


// && and      �� �� �� �� ��     ||  or    �� �� �� �� �� �� �� �� ��