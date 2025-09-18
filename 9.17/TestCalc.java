class TestCalc{
	public static void main(String args[]){

		byte a = 0;
		
		System.out.println( (byte) a);

		int b = a + 500;
	
		System.out.println( (byte) b);

		double c = 1.12345673;
		
		System.out.println( (float)c );

		double d = 1.123456789123456;

		System.out.println( d );

		double f = 1.1234567891234567890; 

		System.out.println( f );

		
	}
}

/*그냥 바이트에는 -128 ~ 127 까지라서 500을 넣으면 오류가 뜨지만
인트 500을 을 바이트로 강제형변환하면 숫자를 돌려서 값을 추출한다

그냥 바이트 500넣었을 때도 숫자를 돌려서 값을 추출하지 않는 이유는뭘까

정수 혹은 실수형데이터 간 구분이 안돼서?


값이 일단 추출돼버리면 틀린지 아닌지 확인하기 어려워서?


강제형변환시 반올림해버린다 정확한 값이 달라짐

*/