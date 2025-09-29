class Answer2{
	public static void main(String args[]){
		int startOffset =3;
		int columns = 7;
		int totalNumbers =31;
		int number =1;

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