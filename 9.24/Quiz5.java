
import java.util.Scanner;
class Quiz5{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("년도");
		scan.nextInt();
		System.out.println("월");
		scan.nextInt();
		System.out.println("시작요일");
		int a = scan.nextInt();
		

	
		swtich(a){
		case1:System.out.println("일 월 화 수 목 금 토");
		System.out.println("1 2 3 4 5 6 7");
		System.out.println("8 9 10 11 12 13 14");
		System.out.println("15 16 17 18 19 20 ");
		System.out.println("21 22 23 24 25 26 27");
		System.out.println("28 29 30 ");
		}
		
	}
}



