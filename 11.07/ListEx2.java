import java.util.*;
class ReverseOrderWithString implements Comparator<String>{
	@Override
	public int compare(String s1,String s2){
		return s1.compareTo(s2)*-1;
	}
}

class ListEx2{
	public static void main(String[] args){
		Vector<String> vec = new Vector<String>();

		vec.add("a");
		vec.add("f");
		vec.add("b");
		vec.add("h");
		vec.add("p");
		vec.add("q");
		vec.add("c");
		//沥纺
		Collections.sort(vec);

		Collections.sort(vec,Collections.reverseOrder());	//府滚胶 规过1
		//Comparator<String> reverseReverseOrderComp = Collections.reverseOrder(new ReverseOrderWithString());
		//Collections.sort(vec,reverseReverseOrderComp); 规过2

		//collections.reverse(vec); 规过3 => sort 饶 reverse 埃窜
		System.out.println(vec);
		System.out.println(Collections.binarySearch(vec,"b"));
		System.out.println(Collections.max(vec));
		System.out.println(Collections.min(vec));
		Collections.shuffle(vec);
		System.out.println(vec);
	}
}