import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ���");
		long input = scan.nextLong();

		if(input % 2 == 0){
			System.out.println("¦���Դϴ�");
			}else if(input % 2 > 0){
			System.out.println("Ȧ���Դϴ�");
			}
	}
}

//������ ¦�� �ƴ� Ȥ���ε� �� else if �� ������� else �������� �ȴ�.

/* import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ���");
		long input = scan.nextLong();

		if(input % 2 == 0){
			System.out.println("¦���Դϴ�");
			}else
			System.out.println("Ȧ���Դϴ�");
			}
	}
}
*/

¦�� Ȥ�� Ȧ���� ��� ��Ű�� ������ ���ڿ��̴�. ������ String result �� ���� ���� �� ����

import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���ڸ� �Է��ϼ���");
		long input = scan.nextLong();

		String result = "Ȧ��";

		if(input % 2 == 0){
				result = "¦��";
			}else 
			System.out.println(result);
	}
}
