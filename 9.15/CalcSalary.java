import java.util.Scanner;

public class CalcSalary {
    public static void main(String[] args){
        
        System.out.println("*10년 저금액 총액 계산기*");
        System.out.println("숫자만 입력하시오 (원단위) ");
        System.out.print("한달 저금액 = ");
        
        Scanner scan; 
        scan = new Scanner(System.in); 
       
        int num1 = scan.nextInt();
        
        int sum = num1 * 12 * 10;
        
        System.out.println("10년 저금액 = " + sum + "원");
        }
    }