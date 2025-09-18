
import java.util.Scanner;

public class VolumeOfACylinder{
	public static void main(String[] args) {
	    
		Scanner scan = new Scanner(System.in);
        
        System.out.println("원기둥의 반지름을 입력하시오");
        
        int a = scan.nextInt();
		
		System.out.println("원기둥의 높이를 입력하시오");
		int b = scan.nextInt();
		
		double c = a * a * b * 3.14;

		System.out.println("원기둥의 부피 = " + c);
	}
}