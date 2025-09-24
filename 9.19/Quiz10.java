import java.util.Scanner;
class Quiz10{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("신용점수");
		int credit   = scan.nextInt();
		System.out.println("연봉");
		int pay      = scan.nextInt();
		System.out.println("부채");
		int debt     = scan.nextInt();

		double result = 1000;

		int highCredit   = 800;
		int middleCredit = 600;
		int highPay      = 7000;
		int middleDebt   = 2000;
		int lowDebt      = 500;
		
		if(credit >= highCredit){
			result = 5000;
		}else if(credit >= middleCredit){
			result = 3000;
		}
		
		if(pay > highPay){
			result *= 1.2;
		}
		if(debt > middleDebt){
			result *= 0.7;
		}
		if(credit >highCredit && debt < lowDebt){
			result *= 1.5;
		}

		System.out.println(result);
	}
}