class StringEx3{
	public static void main(String[] args){
		System.out.println("abc".concat("def"));
	//concat +랑 같음

		String str = "abcde";

		System.out.println(str.contains("x"));
		
		System.out.println("추성빈".contains("추"));	// 포함하는지 판단

		System.out.println(str.endsWith("mn")); // mn으로 끝나는지 판단  txt로 끝나나? 확장자

		System.out.println(str.startsWith("mn")); // mn으로 시작하는지 판단 c로 시작하나? 주소
		// c:\temp\test\a.txt

		System.out.println("abc".equals(new String("abc"))); // 내용 비교 
		String upperCase = "ABC";
		String lowCase = "abc";
		System.out.println(upperCase.equals(lowCase));

		System.out.println(upperCase.toLowerCase()); // 소문자로 바꿈
		System.out.println(lowCase.toUpperCase());	//대문자로바꿈

		System.out.println(upperCase.toLowerCase().equals(upperCase.toLowerCase()));

		System.out.println(upperCase.equalsIgnoreCase(lowCase));	//대소문자 구분없이 비교
	}
}
