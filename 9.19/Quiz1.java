import java.util.Scanner;

class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		System.out.println("¼ıÀÚ");
		int input = scan.nextInt();
	
		String result="0";
		int line = 0;

		if(input>line){
			result = "plus";
		}else if(input<line){
			result = "minus";
		} 
		
		
		System.out.println(result);
	}
}