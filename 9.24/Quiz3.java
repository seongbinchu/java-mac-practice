class Quiz3{
	public static void main(String args[]){
		for(int num=1;num<16;num++){
			System.out.print(num+":");
			for(int num2=1;num2<16;num2++){
				if(num%num2==0){
				System.out.print(num2+" ");
				}
			}System.out.println();
		}

	}
}






