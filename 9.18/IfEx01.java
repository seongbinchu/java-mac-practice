import java.util.Scanner;

	
class IfEx01{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("���ڸ� �Ϸ��Ͻÿ�");
		int input = scan.nextInt();

		if(input > 5) {
			System.out.println("�Է��� ���� 5���� Ů�ϴ�");
		}else if(input == 4){
			System.out.println("�Է��� ���� 4�Դϴ�");
		}else if(input == 3 ){
			System.out.println("�Է��� ���� 3�Դϴ�");
		}else if(input == 2){
			System.out.println("�Է��� ���� 2�Դϴ�");
		}else if(input == 1){
			System.out.println("�Է��� ���� 1�Դϴ�");
		}else {
			System.out.println("�Է��� ���� ����� �ƴմϴ�");
		}

		System.out.println("end of main");

	}
} 