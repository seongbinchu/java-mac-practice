import java.util.Scanner;
//java.util = package 
//API
//class 중복 충돌 막기 위함
public class UserInputCalc {
    
    public static void main(String[] args){
        System.out.print("숫자1 :");
        
        Scanner scan = new Scanner(System.in);
        
        int num1 = scan.nextInt();
        
        System.out.print("숫자2 :");
        
        int num2 = scan.nextInt();
        
        int sum = num1 + num2;
        
        System.out.println("숫자1 + 숫자 2 =" + sum );
        
        
    }
}

//모든 연산은 클래스 범위 안에 존재해야함