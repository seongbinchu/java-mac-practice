import java.util.Scanner;

class Quiz3{
	public static void main(String args[]){

		Scanner scan = new Scanner(System.in);

		System.out.println("��");
		int year = scan.nextInt();
		System.out.println("��");
		int month = scan.nextInt();


		if(month ==1 || month ==3 || month ==5 ||month ==7 ||month ==8 ||month ==10
		||month ==12) {

			System.out.println("���ۿ���");
			int day = scan.nextInt();
			switch(day){
				case 1: 
				System.out.println("��\t��\tȭ\t��\t��\t��\t��");
				for(int a=1;a<8;a++){
					System.out.print(a+"\t");
				}System.out.println();
				for(int a=8;a<15;a++){
					System.out.print(a+"\t");
				}System.out.println();
				for(int a=15;a<22;a++){
					System.out.print(a+"\t");
				}System.out.println();
				for(int a=22;a<29;a++){
					System.out.print(a+"\t");
				}System.out.println();
				for(int a=29;a<32;a++){
					System.out.print(a+"\t");
				}break;
			}
			
		}



	}
}

