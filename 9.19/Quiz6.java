import java.util.Scanner;
class Quiz6{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("���ڸ� �Է��Ͻÿ�");
		int first = scan.nextInt();
		int second= scan.nextInt();
		int third= scan.nextInt();

		String result = "�ﰢ���� �ƴϴ�";
		
		int sum1 = first + second;
		int num2 = first + third;
		int num3 = second + third;

		if(sum1>third && num2>second &&num3>first ) {
			if( first == second && second == third ){
				result = "���ﰢ��";
			}else if(first == second || first == third || second == third){
				result = "�̵�ﰢ��";
			}else{
				result = "�Ϲݻﰢ��";
			}
		}
		System.out.println(result);
	}
}
