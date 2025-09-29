class Step1{
	public static void main(String args[]){
		int number=1;
		int totalNumbers =30;
		int columns =7;
		int offset =3;

		if(offset==3){
			for(int a=0;a<offset;a++){
				System.out.print("\t");
			}System.out.print("1"+"\t"+"2"+"\t"+"3"+"\t"+"4");
			System.out.println();
		}number=5;
		while(number <= totalNumbers){
			
			
			for(int i=0; i<(columns) && number <= totalNumbers; i++){
				System.out.print((number) + "\t");
				number++;
			}
			System.out.println();
		}
	}
}