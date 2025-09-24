import java.util.Scanner;
class Quiz1{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		int input=0;
		boolean flag = true;
		while(flag){
			System.out.println("양수");
			input = scan.nextInt();
			if(input<0){
				flag=false;
			}
		}System.out.println("input");

	}
}

//boolean flag =ture;
//while 조건문안에 ture를 넣고 시작하는것이 자연스럽지않음
//좋은코드라할수없다
/*do{
	반복수행할문장
}while(조건);
quiz1처럼 반복수행을 먼저해보고 조건을 검사해봐야할 경우
do while이 더 좋다 반복수행을 먼저하고 조건을 검사한 후 다시 반복수행하기 때문에
즉 최소한 한번은 실행을 보장한다*/