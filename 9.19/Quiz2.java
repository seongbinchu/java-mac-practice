import java.util.Scanner;

class Quiz2{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("¼ºÀû");
		int score = scan.nextInt();

		String grade = "F";
		int high      = 90;
		int middle    = 80;
		int low       = 70;

		if (score>=high){
			grade = "A";
		}else if(score>=middle){
			grade = "B";
		}else if(score>=low){
			grade = "C";
		}

		System.out.println(grade);
	}
}