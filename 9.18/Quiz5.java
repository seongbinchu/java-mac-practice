import java.util.Scanner;

class Quiz5{
	public static void main(String args[]){


		Scanner scan = new Scanner(System.in);
		System.out.println("Ű���Է��Ͻÿ� ���� (m) ");
		double tall = scan.nextDouble();
		System.out.println("�����Ը� �Է��Ͻÿ�");		
		double kg = scan.nextDouble();


		double bmi = kg / (tall * tall);

		double low = 18.5;
		double middle = 23;
		double high = 25;

		String result;		
		
	
		if(bmi<low){
			result = "��ü��";
		}else if(bmi<middle){
			result = "����";
		}else if(bmi<high){
			result = "��ü��";
		}else{
			result = "��";
		}

		System.out.println("����� bmi��ġ��" +  bmi + "�̸�" + result + "�Դϴ�" );
	}
}



