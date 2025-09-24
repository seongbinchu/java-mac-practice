import java.util.Scanner;
class Quiz8{
	public static void main(String args[]){
		Scanner scan =new Scanner(System.in);

		System.out.println("몸무게");
		int kg = scan.nextInt();
		System.out.println("키");
		int cm = scan.nextInt();
		int bmi = kg / ( (cm/100) * (cm/100) ) ;
	
		String result = "고도 과체중";

		if (bmi < 18.5){
			result = "저체중";
		}else if(bmi < 25){
			result = "정상";
		}else if(bmi > 25 && bmi < 30 ){
			result = "비만";
		}

		System.out.println(result);

	}
}