class IfEx2{
	public static void main(String args[]){
		int first  = 1;
		int second = 2;			
		int third  = 3;
		int fourth = 4;
		int result;

		if(first < second ) {
			if(third < fourth) {
				result = 10;
			} else {
				result = 20;
			}
		} else {
			result = 200;
		}
		System.out.println(result);

	}
}