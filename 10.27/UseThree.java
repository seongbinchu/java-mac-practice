class One{
	public String memberOfOne = "one";
}
class Two extends One{
	public String memberOfTwo = "two";
}
class Three extends Two{
	public String memberOfThree = "three";
}
class UseThree{
	public static void main(String[] args){
		Three t = new Three();
		Two tt = t;
		System.out.println(tt.memberOfTwo);
		//System.out.println(tt.memberOfThree);
		Three ttt = (Three)tt;
		System.out.println(ttt.memberOfThree);
		//three 객체 => two 형변환 => three 형변환 가능

		tt = new Two();
		//ttt = (Three)tt;
		//two 객체 => three 형변환 불가능 => instanceof 사용이유 (다운캐스팅 시 형변환의 안전성을 보장해주는 연산자)
		if(tt instanceof Three){
			ttt = (Three)tt;
		}else{
			System.out.println("변환불가");
		}
	}
}