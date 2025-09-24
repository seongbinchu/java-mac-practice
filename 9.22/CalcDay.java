import java.util.Scanner;
class CalcDay{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("몇월인가");
		int month = scan.nextInt();
		
		int result = 28;
				
		if (month == 1){
			result = 31;
		}else if(month == 3){
			result =31;
		}else if(month == 5){
			result =31;
		}else if(month == 7){
			result =31;
		}else if(month == 8){
			result =31;
		}else if(month == 10){
			result =31;
		}else if(month == 12){
			result =31;
		}else if(month == 4){
			result =30;
		}else if(month == 5){
			result =30;
		}else if(month == 6){
			result =30;
		}else if(month == 11){
			result =30;
		}
		System.out.println(result);
	}
}