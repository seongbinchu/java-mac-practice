class StringEx4{
	
	public static void pointIndices(String words,String key){

		int idx=0;
		boolean flag =true;
		while(flag){
			idx = words.indexOf(key,idx);
			System.out.println(idx);
			if(idx==-1){
				flag=false;
			}
			idx++;
		}

	}
	public static void main(String[] args){
		String str = "abcdabefghiab";

		System.out.println(str.replace("ab","XX")); // ab 찾아서 XX로 바꿈

		System.out.println(str.indexOf("fg"));	//str 중 fg의 순서
		System.out.println(str.indexOf("z"));	//없을 때 -1	
		
		System.out.println(str.indexOf("ab"));	//첫번째걸로 값 나옴

		int idx = str.indexOf("ab");
		System.out.println(idx);
		idx++;

		idx= str.indexOf("ab",idx);		//파라미터 두개로 오버로딩 된 것 idx = idx번째부터 찾아라
		System.out.println(idx);
		idx++;

		idx= str.indexOf("ab",idx);	
		System.out.println(idx);
		
		idx++;
		idx= str.indexOf("ab",idx);	
		System.out.println(idx);

		

		int idx2= str.lastIndexOf("ab");	//제일 뒤에것 찾아짐
		idx--;

		idx = str.lastIndexOf("ab",idx2);
		System.out.println(idx2);

		String words="가나다가나다가나다가나다가나다";
		String key="다";
		
		pointIndices(words,key);
	}
}
