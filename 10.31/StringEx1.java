class StringEx1{
	public static void main(String[] args){
		String str = "abcdefghi";

		System.out.println(str.substring(3));	//3까지제거 후 출력

		System.out.println(str.substring(2,5)); //2부터5전까지출력

		//기본데이터형 -> String (static)
		int num =100;
		String strNum=String.valueOf(num);
		System.out.println(strNum);	//"100"

		/*
			Wrapper-class 기본데이터형 클래스

			byte		Byte
			short		Short
			int			Integer
			long		Long
			float		Float
			double		Double
			char		Character
			boolean		Boolean
		*/

		num=Integer.parseInt(strNum);
		System.out.println(num-1);
		double d = Double.parseDouble("3.14");
		System.out.println(d-1.0);
		
	}
}
