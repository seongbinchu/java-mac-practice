/*case 1: RuntimeException extends
point 1 . Exception name
point 2 . exception or runtimeexception
point 3 . constructor
*/
class NotTenPlus extends RuntimeException{
	public NotTenPlus(String msg){
		super(msg);
	}
	public NotTenPlus(){
		super("NotTenPlus");
	}
	
}
class ExceptionEx2{
	public static void main(String[] args){
		try{
			int n = 0;
			while(true){
				n++;

				if(n>=0){
					NotTenPlus e = new NotTenPlus();
					throw e;
				}
			}
		}catch(NotTenPlus e){
			System.out.println(e.getMessage());
		}
	System.out.println("end of main");
	}
}