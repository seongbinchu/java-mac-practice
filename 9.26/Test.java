import java.util.Scanner;

class Test{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("�⵵");
		int year = scan.nextInt();
		System.out.println("��");
		int month = scan.nextInt();
		System.out.println("���ۿ���");
		String day = scan.next();

		int days = 31;	

		switch(month){
			case 4:
			case 6:
			case 9:
			case 11:
				days = 30;
				break;
			case 2:
				if(( year % 4 == 0 && year !=0 ) || (year % 400 ==0 ) ){
					days=29;
				}else{
					days=28;
				}
		}
		System.out.println();
		System.out.println(year + "�� " + month +"��");
		System.out.println();
		System.out.println("��\t��\tȭ\t��\t��\t��\t��");

		int number = 1;
		int days7  = 7;
		int space  = 0;

		switch(day){
			case "��": space++;
			case "��": space++;
			case "��": space++;
			case "��": space++;
			case "ȭ": space++;
			case "��": space++;
			break;
		}
		if(space > 0 ){
			for(int i=0; i<space; i++ )
			System.out.print("\t");
		}
		while(number<=days){
			for(int d=1; d+space<=days7 && number<=days; d++){
				System.out.print(number+"\t");
				number++;
			}
			space =0;
			System.out.println();
		}

	}
}


