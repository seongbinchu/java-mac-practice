/*
<<interface Collection>>
상속받은 <<interface>>List , <<interface>>Set

<<interface Collection>>
객체 대상
가변 길이

<<interface>>List extends Collection
객체 대상
가변 길이
+a 인덱스

콜렉션 중 가장 많이 쓰이는 두가지 [리스트구현체]
Vector implements List
ArrayList implements List

*thread
*/
import java.util.*;
class ListEx1{
	public static void main(String[] args){
		List<String> list = new ArrayList<String>();

		list.add("a");
		list.add("b");
		list.add("c");
		list.add("d");
		list.add("e");
		list.add("f");
		
		list.add(3,"X");
		
		List<String> subList = new ArrayList<String>();
		subList.add("a");
		subList.add("b");
		subList.add("h");

		//list.addAll(subList);
		//list.addAll(2,subList);

		System.out.println(list.containsAll(subList));
		System.out.println(list);
		
		System.out.println(list.get(2));

		list.remove(1);

		list.set(4,"O");
		System.out.println(list);

		System.out.println(list.subList(2,5));
	}
}
