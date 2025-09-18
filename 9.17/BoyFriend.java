import java.util.Scanner;

class BoyFriend{
	public static void main(String args[]){
	
		Scanner scan = new Scanner(System.in);

		int least    = 170;
		int max      = 185;	
	
		System.out.println("키를 입력하시오");
		
		int man      = scan.nextInt();

		boolean result;

		result = 
		man >= least && max >= man;

		System.out.println(result);
	}
}