import java.util.Scanner;

public class CalcCircle{
    public static void main(String[] args){

        System.out.println("*원의 너비 및 둘레 계산기*");
        System.out.println("*단위를 제외한 숫자만 기입하시오*");
        System.out.print("원의 반지름 = ");

        Scanner scan = new Scanner(System.in);

        double A = scan.nextDouble();
        
        double B = A * A * 3.14;
        double C = A * 2 * 3.14;
        
        System.out.println("원의 너비 = 약 " + B);
        System.out.println("원의 둘레 = 약 " + C);
        

    }
}