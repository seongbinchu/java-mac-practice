import java.util.Scanner;
class Quiz7{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("ù��° 0�� �����ϰ� ���ڸ� ���ڸ��� ������� �����϶�");
		int first = scan.nextInt();
		int second = scan.nextInt();
		int third = scan.nextInt();
		int fourth = scan.nextInt();
		int fiveth = scan.nextInt();

		int sum = first + second + third + fourth + fiveth;

		String result = "Ȧ��";

		if(first == fiveth){
			result = "�յ� ����";
		}else if(sum%2 ==0){
			result = "¦��";
		}
		System.out.println(result);

	}
}