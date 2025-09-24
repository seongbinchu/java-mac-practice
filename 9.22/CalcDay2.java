import java.util.Scanner;
class CalcDay2{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("몇월");
		int month = scan.nextInt();
		
		switch(month) {
			case 1:
			case 3:
			case 5:
			case 8:
			case 10:
			case 12:
				System.out.println("31일");
				break;
			case 4:
			case 6:
			case 9:
			case 11:
				System.out.println("30일");
				break;
			case 2:
				System.out.println("29일");
				break;
			default:
				System.out.println("오류");
		}
	}
}