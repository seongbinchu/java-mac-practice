class Webtoon{
	String firstWebtoonName;
	String firstWebtoonGenre;


	int totalPage(int firstWebtoonPage, int secondWebtoonPage, int thirdWebtoonPage){
		return plus2WebtoonPage( (firstWebtoonPage + secondWebtoonPage),thirdWebtoonPage );
	}

	int plus2WebtoonPage(int firstWebtoonPage, int secondWebtoonPage){
		return firstWebtoonPage + secondWebtoonPage; 
	}
}
class EnterWebtoon{
	public static void main(String agrs[]){

		Webtoon toon1 = new Webtoon();
		toon1.firstWebtoonName   = "��������";
		toon1.firstWebtoonGenre  = "�θǽ�";

		System.out.println(toon1.totalPage(1,2,3));
	}
}
