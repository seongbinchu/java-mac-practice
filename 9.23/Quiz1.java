import java.util.Scanner;
class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int input=0;
		boolean flag = true;
		while(flag){
			System.out.println("���");
			input = scan.nextInt();
			if(input<0){
				flag=false;
			}
		}System.out.println("input");

	}
}

//boolean flag =ture;
//while ���ǹ��ȿ� ture�� �ְ� �����ϴ°��� �ڿ�����������
//�����ڵ���Ҽ�����
/*do{
	�ݺ������ҹ���
}while(����);
quiz1ó�� �ݺ������� �����غ��� ������ �˻��غ����� ���
do while�� �� ���� �ݺ������� �����ϰ� ������ �˻��� �� �ٽ� �ݺ������ϱ� ������
�� �ּ��� �ѹ��� ������ �����Ѵ�*/