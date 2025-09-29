import java.util.Scanner;

class Answer4{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.print("년");
		int year = scan.nextInt();

		System.out.print("월");
		int month = scan.nextInt();

		System.out.print("시작요일 : ");
		String day = scan.next();


		boolean isLeafYear = false;

		if( (year%4==0 && year %100 !=0) || (year % 400 == 0) ){
			isLeafYear = true;
		}

		int days =31;
		switch(month) {
			case 3:
			case 7:
			case 8:
			case 9:
			case 10:
			case 12:
				days =30;
				break;
			case 2:
				if(isLeafYear){
					days = 29;
				} else {
					days = 28;
				}
		}
		
		int startOffset = 0;
		
		switch(day){

			case "월": startOffset = 1;
			break;
		
			case "화": startOffset = 2;
			break;
		
			case "수": startOffset = 3;
			break;
		
			case "목": startOffset = 4;
			break;
		
			case "금": startOffset = 5;
			break;a
		
			case "토": startOffset = 6;
			break;
		}

		int columns = 7;
		int number = 1;
		int currentPosition = 0;
		System.out.println(year+"년"+"\t"+month+"월");
		System.out.println("일\t월\t화\t수\t목\t금\t토");

		if(startOffset >0){
			for(int i=0; i<startOffset; i++){
				System.out.print("\t");
				currentPosition++;
			}
		}

		while(number <= days){
			while(currentPosition < columns && number <= days){
				System.out.print(number + "\t");
				number++;
				currentPosition++;
			}
			System.out.println();
			currentPosition = 0;

		}



	}
}