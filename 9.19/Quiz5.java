import java.util.Scanner;
class Quiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		double year = scan.nextDouble();
		String result = "윤년아님";	

		if(year%4 == 0 && year%100 != 0 || (year%400 ==0) ){
			result = "윤년";
		}
		System.out.println(result);
	}
}


// if (조건문) 조건문에 논리가 여러개 들어가도 되는지? 아니면 &&이나 ||만 여러개 들어갈 수 있는지