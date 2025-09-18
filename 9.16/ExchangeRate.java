import java.util.Scanner;

public class ExchangeRate{
    public static void main(String[] args){
        Scanner scan = new Scanner(System.in);
        
        System.out.println("*원화 달러 변환시스템*");
        System.out.println("*원화를 단위를 제외한 숫자만 기입하시오*");
        System.out.print("원화 : ");
        
        
        int won = scan.nextInt();
    
        double exchangeRate = 1380.4 ;
        
        
        System.out.println("환전된 달러는 = " + won / exchangeRate );
        
    }
}