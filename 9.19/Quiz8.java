import java.util.Scanner;
class Quiz8{
	public static void main(String args[]){
		Scanner scan =new Scanner(System.in);

		System.out.println("������");
		int kg = scan.nextInt();
		System.out.println("Ű");
		int cm = scan.nextInt();
		int bmi = kg / ( (cm/100) * (cm/100) ) ;
	
		String result = "�� ��ü��";

		if (bmi < 18.5){
			result = "��ü��";
		}else if(bmi < 25){
			result = "����";
		}else if(bmi > 25 && bmi < 30 ){
			result = "��";
		}

		System.out.println(result);

	}
}