class Webtoon{
	String firstWebtoonName;
	String firstWebtoonGenre;

	int plus2WebtoonPage(int firstWebtoonPage, int secondWebtoonPage){
		return firstWebtoonPage + secondWebtoonPage; 
	}
	int totalPage(int firstWebtoonPage, int secondWebtoonPage, int thirdWebtoonPage){
		return plus2WebtoonPage( (firstWebtoonPage + secondWebtoonPage),thirdWebtoonPage );
	}

}
class EnterWebtoon{
	public static void main(String agrs[]){

		Webtoon toon1 = new Webtoon();
		toon1.firstWebtoonName   = "연애혁명";
		toon1.firstWebtoonGenre  = "로맨스";

		System.out.println(toon1.totalPage(1,2,3));
	}
}