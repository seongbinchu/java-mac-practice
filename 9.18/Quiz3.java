import java.util.Scanner;

class Quiz3{
	public static void main(String agrs[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("����");
		int first = scan.nextInt();
		System.out.println("����");
		int second = scan.nextInt();
		
		String result = "ũ�Ⱑ �����ϴ�."; // ������ ���� ���� ���� / ���� �ٸ����� ������
						    // ���� ���ٴ� ����
		if(first != second) {		    // ���� �ٸ��� ����
			result = "�� ū����";	    // �����丵
			int max = first;	    // first�� �� ū �������� ����
			if(first < second) {	    // second�� �� ū �������� ����
				max =second;	    
			}
			result += max + "�Դϴ�";   //���� �ٸ� �� ��� �� �����丵
		}
		System.out.println(result);	    // ���� ���� �� 
	}
}

/*

import java.util.Scanner;

class Quiz4{
	public static void main(String agrs[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("����");
		int first = scan.nextInt();
		System.out.println("����");
		int second = scan.nextInt();
		
		if(first>second){
			System.out.println(first +"�� �� ū ���Դϴ�");
		}else if(second > first){
			System.out.println(second +"�� �� ū ���Դϴ�");
		}else{
			System.out.println("���� �����ϴ�");
		}
	}
}
*/
// ���� ������� �ҽ��� �ۼ��� �� �ְ� �����丵�� �����ȯ�� �����ؾ���