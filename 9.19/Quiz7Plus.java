import java.util.Scanner;
class Quiz7Plus{
	public static void main(String agrs[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("����");
		int input = scan.nextInt();

		int a = input/10000;
		int b = ((input/1000) - (a*10) );
		int c = (input/100) - (a*100) - (b*10);
		int d = (input/10) - (a*1000) - (b*100) - (c*10) ;
		int e = input - (a*10000) - (b*1000) - (c*100) - (d*10) ;

		double sum = a+b+c+d+e;

		String result = "Ȧ��";
		if(a==e){
			result = "�յڰ� ��";
		}else if(sum%2 ==0){
			result ="¦��";
		}
		System.out.println("�Է��� �� "+ a+b+c+d+e + "��(��)" + result + "��");
	}
}


