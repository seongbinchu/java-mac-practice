import java.util.Scanner;

class Quiz2{
	public static void main(String args[]){

	System.out.println("나이를 입력하시오");
	Scanner scan = new Scanner(System.in);
	int age = scan.nextInt();

	int child = 0;
	int student = 5000;
	int adult = 10000;
	int old = 3000;


	if(age < 8){
		System.out.println( child + "원입니다");
	}else if (age <= 19){
		System.out.println( student +"원입니다");
		}else if (age < 65){
		System.out.println( adult +"원입니다");
		}else if (age >= 65){
		System.out.println(old + "원입니다");
		}
	


	}




}