import java.util.Scanner;

class OpEx01 {
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("a :");
		int a = scan.nextInt();
		
		System.out.println("b :");
		int b = scan.nextInt();

		String result = "a �� b ����";

		result += (a > b ) ? "ũ��" : "ũ���ʴ�";
		
		System.out.println(result);

	}
}