import java.util.Scanner;

class Quiz3{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("���ڸ� ������� �Է��Ͻÿ�");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();

		int result = third;

		if(first>=second){
			if(first>=third){
				result = first;
				}	
		}else if(second>=first){
			if(second>=third){
				result = second;
			}
		}
		System.out.println("���� ū ���� " + result);
	}
}

//���׿����ڷ� �����Ѱ�?
//if(first>second && first>third) ����