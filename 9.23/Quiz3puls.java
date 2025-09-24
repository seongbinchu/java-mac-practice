import java.util.Scanner;
class Quiz3puls{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		int input;
		boolean flag;
		do{
			System.out.println("Á¤¼öÀÔ·Â");
			input = scan.nextInt();
			flag = input != 0;
			if(flag){
				String result = "È¦¼ö";
				if(input%2==0){
					result="Â¦¼ö";
				}System.out.println(result);
			}
		}while(flag);
		System.out.println("0");
	}
}