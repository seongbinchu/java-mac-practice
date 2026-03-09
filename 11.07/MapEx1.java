/*
Collection		Map(key value) 가변길이
	busan location(value)
index			adress(key) 식별자 중복x
숫자=의무부여x	의무부여 가능
				key+value=>값 비쌈

HashTable,HashMap
*/
import java.util.*;
class MapEx1{
	public static void main(String[] args){
		Map<String,String> map = new Hashtable<String,String>();
		map.put("아","010-1234-2134");
		map.put("에","010-3333-3333");
		map.put("이","010-5555-5555");
		map.put("오","010-7777-7777");
		map.put("이","010-7777-7778");//위의 "이","7777"손실 => 입력하는 데이터가 존재하는지 중요함
		System.out.println(map);
		System.out.println(map.get("이"));

		//String key = "아이유";
		if(!map.containsKey("아이유")){
			map.put("아이유","010-9999-9999");
		}

		System.out.println(map.containsValue("010-7777-7777"));

		System.out.println(map.size());
		//map.clear();
		//System.out.println(map.size());

		//map.remove("아이유"); // => remove 인덱스말고 key로

		//System.out.println(map);
		
		Set<String> keys = map.keySet(); // 모든 key를 꺼내온다. Set(중복허용x key도중복허용x)
		Iterator<String> itr = keys.iterator();
		while(itr.hasNext()){
			String keyObj = itr.next();
			String value = map.get(keyObj);
			System.out.println(keyObj + "," + value);
		}

		Collection<String> values = map.values();
		for(String value : values){
			System.out.println(value);
		}

		System.out.println(map.isEmpty());
	}
}