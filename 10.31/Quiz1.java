/*
	String의 아래 메서드를 활용해서 주석처럼 출력하는 코드를 완성 하시오.
	단, indexOf, lastIndexOf, substring 사용
*/
class Quiz1 {	
	public static void answer1(String str) {
		/*
			태산이
			태산이 높다하되
			태산이 높다하되 하늘
			태산이 높다하되 하늘 아래
			태산이 높다하되 하늘 아래 뫼이로다.
		*/
	}
	public static void answer2(String str) {
		/*
			뫼이로다.
			아래 뫼이로다.
			하늘 아래 뫼이로다.
			높다하되 하늘 아래 뫼이로다.
			태산이 높다하되 하늘 아래 뫼이로다.
		*/
	}
	public static void answer3(String str) {
		/*
			태산이
			높다하되
			하늘
			아래
			뫼이로다.
		*/
	}
	public static void answer4(String str) {
		/*
			뫼이로다
			아래
			하늘
			높다하되
			태산이
		*/
	}
	public static void excuse(String text,String input){
		String result = text.substring(0,text.indexOf(input));
		System.out.println(result);
	}
	public static void excuse2(String text,String input){
		String result = text.substring(text.indexOf(input),20);
		System.out.println(result);
	}
	
	public static void main(String[] args) {
		String str = "태산이 높다하되 하늘 아래 뫼이로다.";

		answer1(str);
		
		int temp=0;
		int num=0;

		while(num!=-1){
			num = str.indexOf(" ",temp);
			if(num!=-1){
				System.out.println(str.substring(0,num));
				temp=num+1;
			}
		}System.out.println(str.substring(0,20));

		temp =20; 
		num=0;
		while(num!=-1){
			num = str.lastIndexOf(" ",temp);
			if(num!=-1){
				System.out.println(str.substring(num,20));
				temp=num-1;
			}
		}System.out.println(str.substring(0,20));
	}
}
