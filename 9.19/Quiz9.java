import java.util.Scanner;
class Quiz9{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("�ſ�����");
		int credit = scan.nextInt();
		System.out.println("����(����)");
		int pay = scan.nextInt();
		System.out.println("�������(%)");
		int debt = scan.nextInt();

		String result = "����Ұ���";

		int deadline = 40;
		int vip = 20;
		int high = 700;
		int middle = 500;
		int paycut = 5000;

		if(debt<deadline){
			if(credit>=high){
				if(debt<vip){
					result="�����̾�";
				}else {
					result ="���Ⱑ��";
				}
			}else if(credit >= middle && pay>=paycut){
				result = "���Ⱑ��";
			}
		}
		System.out.println(result);
	}
}
