import java.util.Scanner;
class Quiz9{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("신용점수");
		int credit = scan.nextInt();
		System.out.println("연봉(만원)");
		int pay = scan.nextInt();
		System.out.println("대출비율(%)");
		int debt = scan.nextInt();

		String result = "대출불가능";

		int deadline = 40;
		int vip = 20;
		int high = 700;
		int middle = 500;
		int paycut = 5000;

		if(debt<deadline){
			if(credit>=high){
				if(debt<vip){
					result="프리미엄";
				}else {
					result ="대출가능";
				}
			}else if(credit >= middle && pay>=paycut){
				result = "대출가능";
			}
		}
		System.out.println(result);
	}
}
