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

/*�׳� ����Ʈ���� -128 ~ 127 ������ 500�� ������ ������ ������
��Ʈ 500�� �� ����Ʈ�� ��������ȯ�ϸ� ���ڸ� ������ ���� �����Ѵ�

�׳� ����Ʈ 500�־��� ���� ���ڸ� ������ ���� �������� �ʴ� �����¹���

���� Ȥ�� �Ǽ��������� �� ������ �ȵż�?


���� �ϴ� ����Ź����� Ʋ���� �ƴ��� Ȯ���ϱ� �������?


��������ȯ�� �ݿø��ع����� ��Ȯ�� ���� �޶���

*/