import java.util.Scanner;

class Test{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);
		
		System.out.println("년도");
		int year = scan.nextInt();
		System.out.println("월");
		int month = scan.nextInt();
		System.out.println("시작요일");
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
		System.out.println(year + "년 " + month +"월");
		System.out.println();
		System.out.println("일\t월\t화\t수\t목\t금\t토");

		int number = 1;
		int days7  = 7;
		int space  = 0;

		switch(day){
			case "토": space++;
			case "금": space++;
			case "목": space++;
			case "수": space++;
			case "화": space++;
			case "월": space++;
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


