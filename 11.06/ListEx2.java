import java.util.*;
class ListEx2{
	public static void main(String[] args){
		Vector<String> vec = new Vector<String>();

		vec.add("a");
		vec.add("b");
		vec.add("c");
		vec.add("d");
		vec.add("e");
		vec.add("f");
		// Vector에 존재하는 기능
		int idx = vec.indexOf("c");
		System.out.println(idx);

		String[] arr = vec.toArray(new String[0]);
		//list =>array
		System.out.println(Arrays.toString(arr));
		//array=>list
		//Arrays.asList();

		List<String> list = Arrays.asList(new String[] {"a","b","c"});
		
		// list.add("x"); 해당 방법 길이 고정시킴
		// 때문에 별 의미 없어보이지만 밑에처럼 해당 원소를 가진 벡터로 변환가능
		Vector<String> vec2 = new Vector<String>(list);
		vec2.add("x");
		
	}
}
