import java.util.Scanner;

public class CalcSalary {
    public static void main(String[] args){
        
        System.out.println("*10�� ���ݾ� �Ѿ� ����*");
        System.out.println("���ڸ� �Է��Ͻÿ� (������) ");
        System.out.print("�Ѵ� ���ݾ� = ");
        
        Scanner scan; 
        scan = new Scanner(System.in); 
       
        int num1 = scan.nextInt();
        
        int sum = num1 * 12 * 10;
        
        System.out.println("10�� ���ݾ� = " + sum + "��");
        }
    }