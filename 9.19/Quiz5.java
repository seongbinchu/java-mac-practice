import java.util.Scanner;
class Quiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		double year = scan.nextDouble();
		String result = "����ƴ�";	

		if(year%4 == 0 && year%100 != 0 || (year%400 ==0) ){
			result = "����";
		}
		System.out.println(result);
	}
}


// if (���ǹ�) ���ǹ��� ���� ������ ���� �Ǵ���? �ƴϸ� &&�̳� ||�� ������ �� �� �ִ���