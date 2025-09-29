import java.util.Scanner;

class Answer4{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.print("��");
		int year = scan.nextInt();

		System.out.print("��");
		int month = scan.nextInt();

		System.out.print("���ۿ��� : ");
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

			case "��": startOffset = 1;
			break;
		
			case "ȭ": startOffset = 2;
			break;
		
			case "��": startOffset = 3;
			break;
		
			case "��": startOffset = 4;
			break;
		
			case "��": startOffset = 5;
			break;a
		
			case "��": startOffset = 6;
			break;
		}

		int columns = 7;
		int number = 1;
		int currentPosition = 0;
		System.out.println(year+"��"+"\t"+month+"��");
		System.out.println("��\t��\tȭ\t��\t��\t��\t��");

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