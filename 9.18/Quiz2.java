import java.util.Scanner;

class Quiz2{
	public static void main(String args[]){

	System.out.println("���̸� �Է��Ͻÿ�");
	Scanner scan = new Scanner(System.in);
	int age = scan.nextInt();

	int child = 0;
	int student = 5000;
	int adult = 10000;
	int old = 3000;


	if(age < 8){
		System.out.println( child + "���Դϴ�");
	}else if (age <= 19){
		System.out.println( student +"���Դϴ�");
		}else if (age < 65){
		System.out.println( adult +"���Դϴ�");
		}else if (age >= 65){
		System.out.println(old + "���Դϴ�");
		}
	


	}




}