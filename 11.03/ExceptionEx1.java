class ExceptionEx1{
	public static void main(String[] args){
		try{
			int n = 0;
			while(true){
				n++;

				if(n>=0){
					Exception e = new Exception("n은 10보다 크거나 같을 수 없다");
					//예외발생시키기 무엇을 상속받을지 정해야함
					throw e;
				}
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
		}
	System.out.println("end of main");
	}
}