import java.util.Scanner;
class OpEx03{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("���ڸ��Է��Ͻÿ�");

		int input = scan.nextInt();

		int point = 5;

		if(input > point){
			System.out.println("���� " + point + " ���� Ů�ϴ�");
		}else {
			System.out.println("���� " + point + " ���� �۽��ϴ�");
		}
		System.out.println("end of main");	
	}
}

/* 

import java.util.Scanner;
class OpEx03{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("���ڸ��Է��Ͻÿ�");

		int input = scan.nextInt();

		if(input > point){
			System.out.println("���� 5���� Ů�ϴ�");
		}else {
			System.out.println("���� 5���� �۽��ϴ�");
		}
		System.out.println("end of main");	
	}
} 

5�� �ߺ��ż� �ҽ��� ������ 5�� �����ϱ� ���ؼ��� �ڵ带 ������ �����ؾ���
������ 5��ġ�� ������ �����������

*/