class Father{
	//밥먹음
	public void eat(){
		//오른손잡이
		System.out.println("오른손으로 밥");
	}
}
class Son extends Father{
	//밥먹음(왼손)
	public void eat(){	//override
		System.out.println("왼손으로 밥");
		/*
		override
		상속관계를 전제로함
		부모와 자식의 일eat은 같으나 방법hand이 다를 때
		접근제한자를 뺀 모든 헤더는 동일해야함(*리턴,메소드이름,파라미터)
		부모보다 접근 제한자는 더 좁아질 수 없다
		+a 인정해줌
		overloading override
		하나의클래스안에서vs부모/자식
		비슷한메소드다르게보기vs비슷한메소드동일하게보기

		eat/ate , 파라미터10개/9개 =>override 확인x
		=> @Override (@ = annotation 주해) 
		*/
		
	}
}
class UseSon{
	public static void main(String[] args){
		Son s = new Son();
		s.eat();
	}
}
