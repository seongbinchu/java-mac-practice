
import java.util.Scanner;

public class VolumeOfACylinder{
	public static void main(String[] args) {
	    
		Scanner scan = new Scanner(System.in);
        
        System.out.println("������� �������� �Է��Ͻÿ�");
        
        int a = scan.nextInt();
		
		System.out.println("������� ���̸� �Է��Ͻÿ�");
		int b = scan.nextInt();
		
		double c = a * a * b * 3.14;

		System.out.println("������� ���� = " + c);
	}
}