class Quiz4Test{
	public static void main(String args[]){
		

		boolean flag = true;

		for(int num=2;num<101;num++){

			for(int a=2;a<num;a++){
				if(num%a==0){
					flag = false;
				}

			}if(flag){
				System.out.println(num);
			}
		}
	}
}

