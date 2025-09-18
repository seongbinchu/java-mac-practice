import java.util.Scanner;

class Quiz5{
	public static void main(String args[]){


		Scanner scan = new Scanner(System.in);
		System.out.println("키를입력하시오 단위 (m) ");
		double tall = scan.nextDouble();
		System.out.println("몸무게를 입력하시오");		
		double kg = scan.nextDouble();


		double bmi = kg / (tall * tall);

		double low = 18.5;
		double middle = 23;
		double high = 25;

		String result;		
		
	
		if(bmi<low){
			result = "저체중";
		}else if(bmi<middle){
			result = "정상";
		}else if(bmi<high){
			result = "과체중";
		}else{
			result = "비만";
		}

		System.out.println("당신의 bmi수치는" +  bmi + "이며" + result + "입니다" );
	}
}



