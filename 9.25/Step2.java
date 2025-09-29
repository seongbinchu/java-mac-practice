class Step2{
	public static void main(String args[]){
		int number=1;
		int totalNumbers =30;
		int columns =7;
		int offset =2;

		if(offset==2){
			for(int a=0;a<offset;a++){
				System.out.print("\t");
			}for(int b=1;b<=columns-offset;b++){
				System.out.print(b+"\t");
			}
			System.out.println();
		}number=8-offset;
		while(number <= totalNumbers){
			
			
			for(int i=0; i<(columns) && number <= totalNumbers; i++){
				System.out.print((number) + "\t");
				number++;
			}
			System.out.println();
		}
	}
}

