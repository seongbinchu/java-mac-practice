class StringEx2{
	public static void main(String[] args){
		String str = "abcdefghiab";
		//			  0123456789
		char c = str.charAt(3);
		System.out.println(c);
		/*
		charAt() = > return값 char
		문자 + 열 :복수 & 순서
		index 존재
		length() = return int 글자수 
		*/
		int length = str.length();
		System.out.println(length);

		for(int i=0; i<str.length();i++){
			System.out.println(str.charAt(i));
		}

		String str1 = "                      abc		";
		System.out.println(str1.length());
		System.out.println(str1.trim().length()); //좌우 끝 공백 삭제
		
	}
}
