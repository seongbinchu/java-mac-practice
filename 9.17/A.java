import java.util.Scanner;

class BoyFriend{
	public static void main(String args[]){
	
		Scanner scan = new Scanner(System.in);
	
		System.out.println("키를 입력하시오");
		
		int man      = scan.nextInt();

		System.out.println( (man >= 170) && (man =<185) );
	}
}
