import java.util.Vector;

public class TestManager{
	public static void main(String[] args){
		HashtagManager manager = new HashtagManager ();

		Vector<String> tags1 = new Vector<String>();
		tags1.add("a");
		tags1.add("b");
		tags1.add("c");
		Doc doc1 = new Doc(tags1,"1st");

		Vector<String> tags2 = new Vector<String>();
		tags2.add("a");
		tags2.add("b");
		Doc doc2 = new Doc(tags2,"2nd");

		Vector<String> tags3 = new Vector<String>();
		tags3.add("z");
		tags3.add("c");
		Doc doc3 = new Doc(tags3,"3td");

		Vector<String> tags4 = new Vector<String>();
		tags4.add("a");
		tags4.add("b");
		Doc doc4 = new Doc(tags4,"4th");
		
		Vector<String> tags5 = new Vector<String>();
		tags5.add("a");
		tags5.add("e");
		Doc doc5 = new Doc(tags5,"5th");

		//back-data setup
		manager.addTag(doc1);
		manager.addTag(doc2);
		manager.addTag(doc3);
		manager.addTag(doc4);
		manager.addTag(doc5);

		Vector<Doc> list;
		list = manager.find("b");
		for(int idx =0 ; idx<list.size() ; idx++){
			System.out.println(list.get(idx));
		}
		System.out.println("=================================");

		list = manager.find("a");
		for(int idx = 0 ; idx <list.size();idx++){
			System.out.println(list.get(idx));
		}
		System.out.println("=================================");

		list = manager.find("c");
		for(int idx = 0 ; idx <list.size();idx++){
			System.out.println(list.get(idx));
		}
		System.out.println("=================================");
		/*list = manager.find("a","b","c");
		for(int idx = 0 ; idx <list.size();idx++){
			System.out.println(list.get(idx));
		}
		System.out.println("=================================");

		list = manager.find("a");
		for(int idx = 0 ; idx <list.size();idx++){
			System.out.println(list.get(idx));
		}
		System.out.println("=================================");*/

		System.out.println(manager.findCoElements("b","a","c"));

	}
}
