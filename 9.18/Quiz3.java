import java.util.Scanner;

class Quiz3{
	public static void main(String agrs[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자");
		int first = scan.nextInt();
		System.out.println("숫자");
		int second = scan.nextInt();
		
		String result = "크기가 같습니다."; // 나오는 값을 값이 같음 / 같이 다름으로 나눈것
						    // 값이 같다는 전제
		if(first != second) {		    // 값이 다름을 전제
			result = "더 큰수는";	    // 리팩토링
			int max = first;	    // first가 더 큰 숫자임을 전제
			if(first < second) {	    // second가 더 큰 숫자임을 전제
				max =second;	    
			}
			result += max + "입니다";   //값이 다를 때 결과 값 리팩토링
		}
		System.out.println(result);	    // 값이 같을 때 
	}
}

/*

import java.util.Scanner;

class Quiz4{
	public static void main(String agrs[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("숫자");
		int first = scan.nextInt();
		System.out.println("숫자");
		int second = scan.nextInt();
		
		if(first>second){
			System.out.println(first +"가 더 큰 수입니다");
		}else if(second > first){
			System.out.println(second +"가 더 큰 수입니다");
		}else{
			System.out.println("값이 같습니다");
		}
	}
}
*/
// 위의 방식으로 소스를 작성할 수 있게 리팩토링과 사고전환을 연습해야함