import java.util.Scanner;
class Quiz3puls{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		int input;
		boolean flag;
		do{
			System.out.println("�����Է�");
			input = scan.nextInt();
			flag = input != 0;
			if(flag){
				String result = "Ȧ��";
				if(input%2==0){
					result="¦��";
				}System.out.println(result);
			}
		}while(flag);
		System.out.println("0");
	}
}