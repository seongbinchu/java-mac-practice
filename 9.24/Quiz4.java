class Quiz4{
	public static void main(String args[]){
		
		
		for(int num=2;num<101;num++){

			boolean flag = true;

			for(int a=2;a<=num/2;a++){
				if(num%a==0){
					flag = false;
				}

			}if(flag){
				System.out.println(num);
			}
		}
	}
}

//소수가아닌수 false에 담김
// true를 뽑으면 소수
// flag&& a*a <num;
//num/2로 나눈 방법 생각해야함
//소수가 가지는 약수는 곱해야하는 반대되는 수가 있음



