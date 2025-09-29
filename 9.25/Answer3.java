import java.util.Scanner;

class Answer3{
	public static void main(String args[]){
		Scanner scan = new Scanner(System.in);

		System.out.println("년도");
		int year = scan.nextInt();
		System.out.println("월");
		int month = scan.nextInt();

		System.out.println("시작요일");
		int startOffset = scan.nextInt();

		int columns =7;
		int number =1;


		if(month ==1 || month ==3 || month ==5 ||month ==7 ||month ==8 ||month ==10
		||month ==12){
			int totalNumbers =31;
			
			int curentPosition = 0;

			if(startOffset > 0){
				for(int i=0; i<startOffset; i++){
					System.out.print("\t");
					curentPosition++;
				}
			}

			while(number <= totalNumbers){
				while(curentPosition < columns && number <=totalNumbers) {
					System.out.print(number + "\t");
					number++;
					curentPosition++;
				}
				System.out.println();
				curentPosition =0;
			}
		

		}

	}
}